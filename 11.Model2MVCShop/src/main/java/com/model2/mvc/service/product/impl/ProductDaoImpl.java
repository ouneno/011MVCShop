package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

// ProductDao CRUD 구현
@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	// Field
	@Autowired // IoC 컨테이너 안에 있는 Bean을 자동으로 주입시켜준다
	@Qualifier("sqlSessionTemplate") // commonservice.xml의 sqlSessionTemplate과 연관, DB연결 및 SQL실행)
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// Constructor
	public ProductDaoImpl() {
		System.out.println("ProductDaoImpl : " +this.getClass());
	}
	
	// Method
	// 1. 상품추가
	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);	
	}
	
	// 2. 상품찾기
	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	
	
	// 3. 상품리스트조회
	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}
	
	
	// 4. 상품수정
	public void updateProduct(Product product) throws Exception {
		System.out.println("여기확인해보기@@@@@ ProductDaoImpl : "+product);
		sqlSession.insert("ProductMapper.updateProduct", product);		
	}

	// 5. 게시판 Page 처리를 위한 전체Row(totalCount)  return 
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
	
}
