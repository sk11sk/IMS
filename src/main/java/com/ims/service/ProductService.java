package com.ims.service;

import java.util.ArrayList;
import java.util.List;

import com.ims.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ims.bo.ProductBO;
import com.ims.bo.VendorBO;
import com.ims.dao.ProductCategoryCustomised;
import com.ims.dao.ProductCustomised;

import com.ims.entity.Product;
import com.ims.entity.Vendor;

@Component
public class ProductService {

	@Autowired
	private  ProductBO productBO;
	
	
	
	@Autowired
	private  VendorBO vendorBO;

	static Logger logger = LoggerFactory.getLogger(ProductService.class);

public ProductRequestDTO addProductDetails(  ProductRequestDTO productRequestDTO) {
	
	logger.info("input : {}", productRequestDTO);

	ProductRequestDTO productDetails = productBO.addProductDetails(productRequestDTO);



	logger.info("response :{}",productDetails);
return productDetails;
}





public ProductDetailsResponseDTO findProductById(String productId) {
	logger.info("input : {}" ,productId);
	    Product product;


			product = productBO.findProductById(productId);

	ProductDetailsResponseDTO  productDetailsResponseDTO = new ProductDetailsResponseDTO();

	    ProductDTO productDTO  = new ProductDTO();
	    productDTO.setProductId(product.getProductId());
	    productDTO.setProductName(product.getProductName());
	    productDTO.setPricePerUnit(product.getPricePerUnit());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setBrand(product.getBrand());
	    productDTO.setBarcode(product.getBarcode());
	    productDTO.setCreatedAt(product.getCreatedAt());
	    productDTO.setUpdatedAt(product.getUpdatedAt());

	productDetailsResponseDTO.setProduct(productDTO);

	    CategoryDto categoryDTO = new CategoryDto();
	    categoryDTO.setCategoryId(product.getCategory().getCategoryId());
	    categoryDTO.setCategoryName(product.getCategory().getCategoryName());
	productDetailsResponseDTO.setCategory(categoryDTO);



	    List<VendorDTO> vendorDTOs = new ArrayList<>();
	     List<Vendor> vendors = product.getVendors();

	for (Vendor vendor : vendors) {
	        VendorDTO vendorDTO = new VendorDTO();
	        vendorDTO.setVendorId(vendor.getCustomerId());
	        vendorDTO.setVendorName(vendor.getVendorName());
	        vendorDTO.setVendorEmail(vendor.getVendorEmail());
	        vendorDTO.setVendorPhone(vendor.getVendorPhone());
	        vendorDTO.setCreatedAt(vendor.getCreatedAt());
	        vendorDTO.setUpdatedAt(vendor.getUpdatedAt());
	        vendorDTOs.add(vendorDTO);
	    }
	    productDetailsResponseDTO.setVendors(vendorDTOs);

	    logger.info("response :{}",productDetailsResponseDTO);
	   return productDetailsResponseDTO;
}


public List<ProductDetailsResponseDTO> findAllProducts() {
	
	logger.info("findAllProducts method triggered");
	
    List<Product> products = productBO.findAllProducts(); // Assuming productBO has a method to fetch all products

	List<ProductDetailsResponseDTO>   productDetailsResponseDTOS= new ArrayList<>();


	for (Product product : products ){

		ProductDetailsResponseDTO  productDetailsResponseDTO = new ProductDetailsResponseDTO();

		ProductDTO productDTO  = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setPricePerUnit(product.getPricePerUnit());
		productDTO.setBarcode(product.getBarcode());
		productDTO.setBrand(product.getBrand());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setCreatedAt(product.getCreatedAt());
		productDTO.setUpdatedAt(product.getUpdatedAt());

		productDetailsResponseDTO.setProduct(productDTO);

		CategoryDto categoryDTO = new CategoryDto();
		categoryDTO.setCategoryId(product.getCategory().getCategoryId());
		categoryDTO.setCategoryName(product.getCategory().getCategoryName());
		productDetailsResponseDTO.setCategory(categoryDTO);



		List<VendorDTO> vendorDTOs = new ArrayList<>();
		List<Vendor> vendors = product.getVendors();

		for (Vendor vendor : vendors) {
			VendorDTO vendorDTO = new VendorDTO();
			vendorDTO.setVendorId(vendor.getCustomerId());
			vendorDTO.setVendorName(vendor.getVendorName());
			vendorDTO.setVendorEmail(vendor.getVendorEmail());
			vendorDTO.setVendorPhone(vendor.getVendorPhone());
			vendorDTO.setCreatedAt(vendor.getCreatedAt());
			vendorDTO.setUpdatedAt(vendor.getUpdatedAt());
			vendorDTOs.add(vendorDTO);
		}
		productDetailsResponseDTO.setVendors(vendorDTOs);
		productDetailsResponseDTOS.add(productDetailsResponseDTO);
	}



    logger.info("response :{}",productDetailsResponseDTOS);
    return productDetailsResponseDTOS;
}


public List<ProductCategoryCustomised> findProductByCategory(String categoryName) {
	
	logger.info("input : {}" ,categoryName);
	
	List<ProductCategoryCustomised> productByCategory=	productBO.findProductByCategory(categoryName);
	
	 for (ProductCategoryCustomised products : productByCategory) {
			
			logger.info("category  Name: {}, product name : {}  ",
			     
			        products.getCategoryName(), 
			         
			        products.getProductName()
			        
			    );
	 }
	return productByCategory;
}


public Long countProductsByCategoryName(String categoryName) {
	
	logger.info("input : {}" ,categoryName);
	Long productsCount= productBO.countProductsByCategoryName(categoryName);
	
	logger.info("response :{}",productsCount);
	return productsCount;
}


public Double findAverageProductPriceByCategoryName(String categoryName) {
	
	logger.info("input : {}" ,categoryName);
	Double  productsAvg= productBO.findAverageProductPriceByCategoryName(categoryName);
	
	logger.info("response :{}",productsAvg);
	return productsAvg;
}


public List<ProductCustomised> getProductsPriceInAscOrder() {
	logger.info("getProductsPriceInAscOrder  method triggered");
	
	List<ProductCustomised> productsInAscOrder =	productBO.getProductsPriceInAscOrder();
	
	 for (ProductCustomised products : productsInAscOrder) {
			
			logger.info(" product Name: {}, price: {}",
			     
			        products.getProductName(), 
			         
			        products.getPrice()
			    );
	 }
	return productsInAscOrder;
}


public List<ProductCategoryCustomised> countProductsByCategory() {
	logger.info("countProductsByCategory  method triggered");
	 List<ProductCategoryCustomised> countProductsByCategory =	productBO.countProductsByCategory();
	 
	 for (ProductCategoryCustomised products : countProductsByCategory) {
			
			logger.info(" Category Name: {}, total products: {}",
			     
			        products.getCategoryName(), 
			         
			        products.getTotalProducts()
			    );
	 }
	return countProductsByCategory;
}


public List<ProductCategoryCustomised> categoryProductsSum() {
	logger.info("categoryProductsSum  method triggered");
	List<ProductCategoryCustomised> countProductsByCategory =	productBO.categoryProductsSum();
	
	for (ProductCategoryCustomised products : countProductsByCategory) {
		
			logger.info(" Category Name: {}, Product Amount: {}",
			     
			        products.getCategoryName(), 
			         
			        products.getProductAmount()
			    );	
			
			
		
	    
	}
	
	return countProductsByCategory;
}


public List<ProductCustomised> findProductAssociatedTovendors() {
	logger.info("countProductsByCategory  method triggered");
	List<ProductCustomised> productAssociatedTovendors = productBO.findProductAssociatedTovendors();
	 for (ProductCustomised products : productAssociatedTovendors) {
			
			logger.info("vendor  Name: {}, product name : {}",
			     
					  products.getVendorName(),
			        products.getProductName()
			         
			        
			    );
	 }
	return productAssociatedTovendors;
}


public List<ProductDetailsResponseDTO> findProductByName(String name) {
	
	logger.info("input : {}" ,name);
	List<Product> products = productBO.findProductByName(name);

	List<ProductDetailsResponseDTO>   productDetailsResponseDTOS= new ArrayList<>();


	for (Product product : products ){

		ProductDetailsResponseDTO  productDetailsResponseDTO = new ProductDetailsResponseDTO();

		ProductDTO productDTO  = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setPricePerUnit(product.getPricePerUnit());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setBrand(product.getBrand());
		productDTO.setBarcode(product.getBarcode());
		productDTO.setCreatedAt(product.getCreatedAt());
		productDTO.setUpdatedAt(product.getUpdatedAt());

		productDetailsResponseDTO.setProduct(productDTO);

		CategoryDto categoryDTO = new CategoryDto();
		categoryDTO.setCategoryId(product.getCategory().getCategoryId());
		categoryDTO.setCategoryName(product.getCategory().getCategoryName());
		productDetailsResponseDTO.setCategory(categoryDTO);



		List<VendorDTO> vendorDTOs = new ArrayList<>();
		List<Vendor> vendors = product.getVendors();

		for (Vendor vendor : vendors) {
			VendorDTO vendorDTO = new VendorDTO();
			vendorDTO.setVendorId(vendor.getCustomerId());
			vendorDTO.setVendorName(vendor.getVendorName());
			vendorDTO.setVendorEmail(vendor.getVendorEmail());
			vendorDTO.setVendorPhone(vendor.getVendorPhone());
			vendorDTO.setCreatedAt(vendor.getCreatedAt());
			vendorDTO.setUpdatedAt(vendor.getUpdatedAt());
			vendorDTOs.add(vendorDTO);
		}
		productDetailsResponseDTO.setVendors(vendorDTOs);
		productDetailsResponseDTOS.add(productDetailsResponseDTO);
	}




	logger.info("response :{}",productDetailsResponseDTOS);
	    return productDetailsResponseDTOS;


}


public List<ProductDTO> findAllProductByNameDescending() {
	logger.info("findAllProductByNameDescending  method triggered");
	List<Product> productList = productBO.findAllProductByNameDescending();
	
	
	 List<ProductDTO> productDtos   = new ArrayList<>();

			 for (Product product :productList){


				 ProductDTO productDTO  = new ProductDTO();
				 productDTO.setProductId(product.getProductId());
				 productDTO.setProductName(product.getProductName());
				 productDTO.setPricePerUnit(product.getPricePerUnit());
				 productDTO.setQuantity(product.getQuantity());
				 productDTO.setBrand(product.getBrand());
				 productDTO.setBarcode(product.getBarcode());
				 productDTO.setCreatedAt(product.getCreatedAt());
				 productDTO.setUpdatedAt(product.getUpdatedAt());

				 productDtos.add(productDTO);
			 }


	 logger.info("response :{}",productDtos);
	 return productDtos;
	}


public List<ProductCustomised> findProductAssociatedTovendorsWithCategory() {
	
	logger.info("findProductAssociatedTovendorsWithCategory  method triggered");
	    List<ProductCustomised> productAssociatedTovendorsWithCategory = productBO.findProductAssociatedTovendorsWithCategory();
	    
 for (ProductCustomised products : productAssociatedTovendorsWithCategory) {
			
			logger.info("vendor  Name: {}, product name : {} , category name : {} ",
			     
			        products.getVendorName(), 
			         
			        products.getProductName(),
			        products.getCategory()
			    );
	 }
	return productAssociatedTovendorsWithCategory;
}



}



