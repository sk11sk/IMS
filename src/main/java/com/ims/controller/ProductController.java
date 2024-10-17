package com.ims.controller;

import java.util.Arrays;
import java.util.List;

import com.ims.dto.ProductDetailsResponseDTO;
import com.ims.dto.ProductRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ims.dao.ProductCategoryCustomised;
import com.ims.dao.ProductCustomised;
import com.ims.dto.ProductDTO;

import com.ims.dto.ValidateResponse;
import com.ims.entity.Category;
import com.ims.entity.Product;
import com.ims.entity.Vendor;
import com.ims.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	

	
	static Logger logger  = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ValidateResponse validateResponse;

	// http://localhost:8080/api/product/addProductDetails
	@PostMapping("/addProductDetails")
	public ProductRequestDTO addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
		logger.info("addProduct method triggered with dto values as : {}", productRequestDTO);


	
		logger.info("saving the productDetailsDTO  : {}", productRequestDTO);

		ProductRequestDTO productDetails = productService.addProductDetails( productRequestDTO);
	
		logger.info("reponse from addProduct method record created as  : {}",productDetails);

		return productDetails;

	}

	// http://localhost:8080/api/product/findProductById/1

	// @RequestMapping(value = "/findProductById/{Id}", method = RequestMethod.GET)
	
	@GetMapping("/findProductById/{productId}")
	
	public ResponseEntity<?> findProductById(@PathVariable String productId) {
		
		logger.info("findProductById method triggered with input  : {}", productId);

		ProductDetailsResponseDTO productDetails = productService.findProductById(productId);

		
		
		
		if (productDetails==null) {
			
			logger.error(validateResponse.getMessage(),"productDTO is null");
			
			return  new ResponseEntity<> (validateResponse.getMessage(),HttpStatus.BAD_REQUEST);
	    }
		 
		logger.info("reponse  : {}",productDetails);
		
			return  new ResponseEntity<> (productDetails,HttpStatus.OK);
			
			

	}

	// http://localhost:8080/api/product/findAllProducts

	@RequestMapping(value = "/findAllProducts", method = RequestMethod.GET)
	public List<ProductDetailsResponseDTO> getAllProducts() {
		logger.info("getAllProducts method triggered ");
		
		List<ProductDetailsResponseDTO> allProducts = productService.findAllProducts();
		
		logger.info("reponse  : {}",allProducts);
		return allProducts;
		
	}

// http://localhost:8080/api/product/findProductByName/wooden pot

	@RequestMapping(value = "/findProductByName/{name}", method = RequestMethod.GET)
	
	public ResponseEntity<?> findProductByName(@PathVariable(required = false) String name) {
		logger.info("findProductByName method triggered with input  : {}", name);
		List<ProductDetailsResponseDTO> products = productService.findProductByName(name);
		
		
		if (products.isEmpty()) {
			
			logger.error(validateResponse.getMessage(), "list is empty ");
			
			return  new ResponseEntity<> (validateResponse.getMessage(),HttpStatus.BAD_REQUEST);
	    }
		logger.info("reponse  : {}",products);
		
			return  new ResponseEntity<> (products,HttpStatus.OK);
	}

	// http://localhost:8080/api/product/findAllProductByNameDescending
	@RequestMapping(value = "/findAllProductByNameDescending", method = RequestMethod.GET)
	public List<ProductDTO> findAllProductByNameDescending() {
		logger.info("findAllProductByNameDescending method triggered" );
		List<ProductDTO> products = productService.findAllProductByNameDescending();
		
		logger.info("reponse : {}",products);
		return products;
	}

	// http://localhost:8080/api/product/findProductByCategory/Books

	@RequestMapping(value = "/findProductByCategory/{categoryName}", method = RequestMethod.GET)
	public  ResponseEntity<?> findProductByCategory(@PathVariable String categoryName) {

		logger.info("findProductByCategory method triggered with input  : {}", categoryName);
		List<ProductCategoryCustomised> products = productService.findProductByCategory(categoryName);
		
       if (products.isEmpty()) {
    	   
    	   logger.error(validateResponse.getMessage(),"list is empty ");

			return  new ResponseEntity<> (validateResponse.getMessage(),HttpStatus.NOT_FOUND);
	    }
       logger.info("reponse  : {}",products);
			return  new ResponseEntity<> (products,HttpStatus.OK);

	}

	// http://localhost:8080/api/product/productsCount/Sports
	@RequestMapping(value = "/productsCount/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<?>countProductsByCategoryName(@PathVariable String categoryName) {
		logger.info("countProductsByCategoryName method triggered with input  : {}", categoryName);
		

		Long productsCount = productService.countProductsByCategoryName(categoryName);
		
       if (productsCount==null) {
    	  
    	   String message = validateResponse.getMessage();
    	   
    	   logger.error(message,"productsCount is null");
    	   
    		return  new ResponseEntity<> (message,HttpStatus.NOT_FOUND);
		}
       logger.info("reponse  : {}",productsCount);

		return  new ResponseEntity<> ("total products in the category:  " +categoryName + " are :" + productsCount,HttpStatus.OK);

	}

	// http://localhost:8080/api/product/averageProductPriceByCategory/Sports
	@RequestMapping(value = "/averageProductPriceByCategory/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<?> findAverageProductPriceByCategoryName(@PathVariable String categoryName) {
		
		logger.info("findAverageProductPriceByCategoryName method triggered with input  : {}", categoryName);
		
		Double averageProductPriceByCategoryName = productService.findAverageProductPriceByCategoryName(categoryName);

		 if (averageProductPriceByCategoryName==null) {
	    	  
	    	   String message = validateResponse.getMessage();
	    	   logger.error(message,"averageProductPriceByCategoryName is null");
	    		return  new ResponseEntity<> (message,HttpStatus.NOT_FOUND);
			}

		 logger.info("reponse : {}",averageProductPriceByCategoryName);
			return  new ResponseEntity<> ("avg of products  price in the category:  " + categoryName + " is :" + averageProductPriceByCategoryName,HttpStatus.OK);

	}

	// http://localhost:8080/api/product/getProductsPriceInAscOrder
	@RequestMapping(value = "/getProductsPriceInAscOrder", method = RequestMethod.GET)
	public List<ProductCustomised> getProductsPriceInAscOrder() {
		logger.info("getProductsPriceInAscOrder method triggered");
		
		List<ProductCustomised> productsInAscOrder = productService.getProductsPriceInAscOrder();
		
		logger.info("list fetched sucess fully");
		return productsInAscOrder;

	}

	// http://localhost:8080/api/product/countProductsByCategory

	@RequestMapping(value = "/countProductsByCategory", method = RequestMethod.GET)
	public List<ProductCategoryCustomised> countProductsByCategory() {
		logger.info("countProductsByCategory method triggered ");
		List<ProductCategoryCustomised> countProductsByCategory = productService.countProductsByCategory();
		
		logger.info("list fetched sucess fully");
		return countProductsByCategory;

	}

// http://localhost:8080/api/product/categoryProductsSum

	@RequestMapping(value = "/categoryProductsSum", method = RequestMethod.GET)
	public List<ProductCategoryCustomised> categoryProductsSum() {
		
		logger.info("categoryProductsSum method triggered ");
		
		List<ProductCategoryCustomised> categoryProductsSum = productService.categoryProductsSum();
		
		logger.info("list fetched sucess fully");
		return categoryProductsSum;

	}

	// http://localhost:8080/api/product/productAssociatedTovendors

	@RequestMapping(value = "/productAssociatedTovendors", method = RequestMethod.GET)
	public List<ProductCustomised> findProductAssociatedTovendors() {
		
		logger.info("findProductAssociatedTovendors method triggered");
		
		List<ProductCustomised> productAssociatedTovendors = productService.findProductAssociatedTovendors();
		
		logger.info("list fetched sucess fully");
		return productAssociatedTovendors;

	}

// http://localhost:8080/api/product/ProductAssociatedTovendorsWithCategory

	@RequestMapping(value = "/ProductAssociatedTovendorsWithCategory", method = RequestMethod.GET)
	public List<ProductCustomised> findProductAssociatedTovendorsWithCategory() {
		
		logger.info("findProductAssociatedTovendorsWithCategory method triggered ");
		
		List<ProductCustomised> productAssociatedTovendorsWithCategory = productService
				.findProductAssociatedTovendorsWithCategory();
		
		logger.info("list fetched sucesfully");
		return productAssociatedTovendorsWithCategory;

	}

}
