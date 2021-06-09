package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

// 상품관리를 CRUD로 추상화, 캡슐화
// Dao Interface 정의
public interface ProductDao {
	
	// 1. 상품추가
	public void addProduct(Product product) throws Exception;
	
	// 2. 상품조회
	public Product getProduct(int prodNo) throws Exception;
	
	// 3. 상품리스트
	public List<Product> getProductList(Search search) throws Exception;
	
	// 4. 상품수정
	public void updateProduct(Product product) throws Exception;
	
	// 5. 게시판 Page 처리를 위한 전체Row(totalCount) return
	public int getTotalCount(Search search) throws Exception;

}
