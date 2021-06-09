package com.model2.mvc.service.product;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

// 상품관리 서비스 할 내용
// Product Service Interface 정의
public interface ProductService {
	
	// 1. 상품추가
	public void addProduct(Product product) throws Exception;
	
	// 2. 상품조회
	public Product getProduct(int prodNo) throws Exception;
	
	// 3. 상품리스트
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	// 4. 상품수정
	public void updateProduct(Product product) throws Exception;

}
