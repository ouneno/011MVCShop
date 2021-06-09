package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

// 상품관리 서비스 구현
// @Service : 해당 클래스를 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션입니다
@Service("productServiceImpl") 
public class ProductServiceImpl implements ProductService {
	
	// Field
	@Autowired // IoC 컨테이너 안에 있는 Bean을 자동으로 주입시켜준다
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	// Constructor
	public ProductServiceImpl() {
		System.out.println("ProductServiceImpl : " +this.getClass());
	}

	// Method
	// 1. 상품추가
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
	}
	
	// 2. 상품조회
	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}
	
	// 3. 상품리스트
	public Map<String, Object> getProductList(Search search) throws Exception {
				
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	// 4. 상품수정
	public void updateProduct(Product product) throws Exception {
		System.out.println("여기확인해보기@@@@@ ProductServiceImpl : "+product);
		productDao.updateProduct(product);
	}
	
}
