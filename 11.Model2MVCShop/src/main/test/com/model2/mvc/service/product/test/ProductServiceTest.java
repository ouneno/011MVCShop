package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

/*
  	@RunWith : JUnit 테스트 할 때 필요한 Annotaion
 
  	@ContextConfiguration : Bean Context를 불러온다, 어노테이션의 location에는 classpath가 들어간다
  							commonservice.xml의 Line 43
  							<context:component-scan base-package="com.model2.mvc.service"/>
*/
@RunWith(SpringJUnit4ClassRunner.class)

// 다양하진 Meta-data로인하여 기재 할 xml이 늘어났다
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	// @RunWith, @ContextConfiguration을 이용하여 Wiring
	// Test를 실행 할 인스턴스 DI
		/* 	
		 	@Autowired : IoC 컨테이너 안에 있는 Bean을 자동으로 주입시켜준다
		 	@Qualifier : commonservice.xml의 sqlSessionTemplate과 연관, DB연결 및 SQL실행)
		*/
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	// 1. 상품추가
	// 상품명, 상세정보, 제조일자, 가격, 상품이미지
	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("Americano");
		product.setProdDetail("coffee");
		product.setManuDate("testManuDate");
		product.setPrice(3333);
		product.setFileName("testFileName");
		
		productService.addProduct(product);
		
		product = productService.getProduct(10000);
		
		Assert.assertEquals("Americano", product.getProdName());
		Assert.assertEquals("coffee", product.getProdName());
		Assert.assertEquals("testManuDate", product.getProdDetail());
		Assert.assertEquals(3333, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());
		
	}
	
	// 2. 상품조회
	// ProdNo, 상품명, 상세정보, Manu, price, image, reg
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		//product.setProdNo(10050);
		//product.setProdName("testProduct");
		//product.setProdDetail("testDetail");
		//product.setManuDate("testManuDate");
		//product.setPrice(2000);
		//product.setFileName("testFileName");
		//product.setRegDate(null);
		
		product = productService.getProduct(10000);
		System.out.println("product : "+product);
		
		Assert.assertEquals(10001, product.getProdNo());
		Assert.assertEquals("자전거", product.getProdName());
		//Assert.assertEquals("testDetail", product.getProdDetail());
		//Assert.assertEquals("testManuDate", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		//Assert.assertEquals("10000", product.getFileName());
		//Assert.assertEquals(null, product.getRegDate());
		
		Assert.assertNotNull(productService.getProduct(10000));
		Assert.assertNotNull(productService.getProduct(10001));		
	}
	
	// 3. 상품리스트
	//@Test
	public void testGetProductListAll() throws Exception {

		Search search = new Search();
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console 확인
		System.out.println("ProductServiceTest, getProductList : " + list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console 확인
		System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}
	
	// 4. 상품수정
	//@Test
	public void testUpdateProduct() throws Exception {

		Product product = productService.getProduct(10007);
		System.out.println("testUpdateProduct_____ : " +product);
		
		Assert.assertNotNull(product);

		Assert.assertEquals("삼성센스", product.getProdName());
		Assert.assertEquals("노트북", product.getProdDetail());
		Assert.assertEquals("20120212", product.getManuDate());
		Assert.assertEquals(600000, product.getPrice());
		//Assert.assertEquals(null, product.getRegDate());

		product.setProdName("PARKYOULA");
		product.setProdDetail("youla is student");
		product.setManuDate("2021");
		product.setPrice(1);
		//product.setRegDate(null);

		productService.updateProduct(product);

		product = productService.getProduct(10007);
		Assert.assertNotNull(product);

		// ==> console 확인
		System.out.println("product_updateProduct : " + product);

		// ==> API 확인

		Assert.assertEquals("changeProdName", product.getProdName());
		Assert.assertEquals("changeProdDetail", product.getProdDetail());
		Assert.assertEquals("changeManuDate", product.getManuDate());
		Assert.assertEquals(2, product.getPrice());
		Assert.assertEquals("changeRegDate", product.getRegDate());
	}

}