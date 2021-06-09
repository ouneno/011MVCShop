package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println("07.MVCModel :: ProductController 진입완료");
	}
	
	// PageSize 정하기
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	// 1. addProduct.jsp
	@RequestMapping( value="addProduct", method=RequestMethod.POST )
	public String addProduct(@ModelAttribute("product") Product product) throws Exception {

		System.out.println("/product/addProduct : POST");
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.addProduct(product);
	
		return "forward:/product/addProduct.jsp";
	}
	
	
	//2. getProduct.jsp
	@RequestMapping( value="getProduct", method=RequestMethod.GET )
	public String getProduct(	@RequestParam("prodNo") int prodNo, Model model, 
								HttpServletRequest request) throws Exception {
		
		System.out.println("/product/getProduct : GET");

		Product product = productService.getProduct(prodNo);
		
		String menu = request.getParameter("menu");
		request.setAttribute("menu", menu);
		
		model.addAttribute("product", product);
			
		return "forward:/product/getProduct.jsp";
	}
	
	
	
	// 3. updateProductView.jsp
	@RequestMapping( value="updateProductView", method=RequestMethod.GET )
	public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		System.out.println("/product/updateProductView : GET");
		
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);

		return "forward:/product/updateProductView.jsp";
	}
	
	
	
	// 4. updateProduct.jsp
	@RequestMapping( value="updateProduct", method=RequestMethod.GET )
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		System.out.println("/product/updateProduct : GET");

		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);

		return "forward:/product/updateProduct.jsp";
	}
	
	
	
	// 5. listProduct.jsp
	@RequestMapping( value="listProduct" )
	public String listProduct(	@ModelAttribute("search") Search search,
								Model model, HttpServletRequest request)
										throws Exception {

		System.out.println("/product/listProduct : GET / POST");
		
		String menu = request.getParameter("menu");
		request.setAttribute("menu", menu);
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String, Object> map = productService.getProductList(search);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
				
		return "forward:/product/listProduct.jsp";
	}

}
