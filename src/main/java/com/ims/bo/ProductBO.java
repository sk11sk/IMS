package com.ims.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ims.dao.*;
import com.ims.dto.CategoryDto;
import com.ims.dto.ProductDTO;

import com.ims.dto.ProductRequestDTO;
import com.ims.dto.ValidateResponse;
import com.ims.entity.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.ims.exception.ResourceNotFoundException;

@Component
public class ProductBO {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ValidateResponse validateResponse;

	@Autowired
	ProductTransactionLogRepository productTransactionLogRepository;


	@Autowired
	PurchaseRepository purchaseRepository;

	 static Logger log = LoggerFactory.getLogger(ProductBO.class);
	
	

	

	private static boolean isPositiveInteger(String input) {

		log.debug(input);
		
		
		return input.matches("\\d+") && Integer.parseInt(input) > 0;
	}

	public Product findProductById(String productId) throws ResourceNotFoundException {
		
		log.info("input :{}", productId);
		Product product = null;
		String input = productId;

		if (isPositiveInteger(input)) {

			long productID = Integer.parseInt(input);

			product = productRepository.findById(productID)

					.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productID + " doesn't exist"));

		} else {

			validateResponse.setMessage("Invalid input! Please enter a positive integer greater than zero.");
			log.error("Invalid input! Please enter a positive integer greater than zero.");

		}
		log.info(" product by id  fetched sucessfuly");
		return product;

	}

	public List<Product> findProductByName(String name) {
		
		log.info("input :{}", name);
		List<Product> productListBYName = null;

		if (name == null || name.trim().isEmpty() || name.equals("null")) {

			validateResponse.setMessage("product name can not be null , enter valid product name ");
			log.error("product name can not be null , enter valid product name ");
			log.debug(name);

			productListBYName=  Collections.emptyList();


		} else {

			productListBYName = productRepository.findProductByName(name);

			if (productListBYName.isEmpty()) {
				
				log.error("product name doesnt matches in the database Re-enter valid product name");
				log.debug(name);
				throw new ResourceNotFoundException(
						"product name doesnt matches in the database Re-enter valid product name");

			}

		}
		log.info("list fetched sucessfuly");
		return productListBYName;
	}

	public List<Product> findAllProductByNameDescending() {
		
		log.info("findAllProductByNameDescending method triggered");
		
		List<Product> allProductByNameDescending = productRepository.findAllProductByNameDescending();
		
		if (allProductByNameDescending.isEmpty()) {
			
			log.error("no records to show because list is empty  ");
			
			throw new ResourceNotFoundException("no records to show because list is empty  ");
		}
		
		log.info("list fetched sucessfuly");
		return allProductByNameDescending;
	}

	public List<ProductCustomised> findProductAssociatedTovendors() {
		
		log.info("findProductAssociatedTovendors method triggered");
		
		List<ProductCustomised> productAssociatedTovendors = productRepository.findProductAssociatedTovendors();
		
		log.info("list fetched sucessfuly");
		return productAssociatedTovendors;
	}

	public List<ProductCustomised> findProductAssociatedTovendorsWithCategory() {
		log.info("findProductAssociatedTovendorsWithCategory is triggered");
		
		List<ProductCustomised> productAndCategory = productRepository.findProductAssociatedTovendorsWithCategory();
		
		log.info("list fetched sucessfuly");
		return productAndCategory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ProductRequestDTO addProductDetails( ProductRequestDTO productRequestDTO) {
		log.info("input :{}", productRequestDTO);

		ProductRequestDTO  productRequestDTOs = new ProductRequestDTO() ; //for response
		List<ProductDTO> productResponseDtos = new ArrayList<>();  // for response

		Category existingCategory =null;

		Long vendorId = productRequestDTO.getVendorId();

		String vendorName = productRequestDTO.getVendorName();



		Optional<Vendor>  vendorById = vendorRepository.findByVendorIdAndVendorName(vendorId,vendorName);


		Vendor existingVendor = vendorById.orElseThrow(() -> new ResourceNotFoundException("vendor  with ID and name  doesn't exist"));

		if(vendorById.isPresent()){


			CategoryDto categoryDto = productRequestDTO.getCategory();
			String categoryName = categoryDto.getCategoryName();

			Optional<Category> categoryById = categoryRepository.findByCategoryName(categoryName);


			if (categoryById.isPresent()){

				existingCategory = categoryById.orElseThrow(() -> new ResourceNotFoundException("entered category doesn't exist"));
                                                     //.get();
				List<ProductDTO> productDTO = productRequestDTO.getProducts();

				for (ProductDTO productDto :productDTO) {


					Optional<Product> productByName = productRepository.findByProductNameAndBrand(productDto.getProductName(),productDto.getBrand());
					// product already present

					if (productByName.isPresent()) {

						Product existingProduct = productByName.orElseThrow(() -> new ResourceNotFoundException("product  name  doesn't exist"));

						long quantity = existingProduct.getQuantity();
						long updatedQuantity = 0;

						updatedQuantity =  quantity+productDto.getQuantity();

						existingProduct.setQuantity(updatedQuantity);


						existingProduct.getVendors().add(existingVendor);
						existingVendor.getProducts().add(existingProduct);

						vendorRepository.save(existingVendor);
            log.info("existing product found so  quantity is updated  and saved with the vendor    : {}",productDto.getProductName() + existingVendor.getVendorName());

						// update purchase table

						Purchase purchase = new Purchase();

//						purchase.setProductId(existingProduct.getProductId());
//						purchase.setPurchasedQuantity(productDto.getQuantity());


						purchase.setProduct(existingProduct);
						existingProduct.getPurchases().add(purchase);

						purchase.setVendor(existingVendor);
						existingVendor.getPurchases().add(purchase);
						purchase.setPurchasedQuantity(productDto.getQuantity());
						purchaseRepository.save(purchase);


						ProductTransactionLog productTransactionLog = new ProductTransactionLog();
						productTransactionLog.setQuantityChanged(productDto.getQuantity());
						productTransactionLog.setTransactionType("added");
						productTransactionLog.setProduct(existingProduct);
						existingProduct.getProductTransactionLog().add(productTransactionLog);
						productTransactionLogRepository.save(productTransactionLog);


						// response


						ProductDTO productResponseDto = new ProductDTO();
						productResponseDto.setProductId(existingProduct.getProductId());
						productResponseDto.setProductName(existingProduct.getProductName());
						productResponseDto.setQuantity(productDto.getQuantity());
						productResponseDto.setBrand(existingProduct.getBrand());
						productResponseDto.setBarcode(existingProduct.getBarcode());
						productResponseDto.setPricePerUnit(existingProduct.getPricePerUnit());
						productResponseDto.setCreatedAt(existingProduct.getCreatedAt());
						productResponseDto.setUpdatedAt(existingProduct.getUpdatedAt());

                       productResponseDtos.add(productResponseDto);





					} else {

						Product newProduct = new Product();


						newProduct.setProductName(productDto.getProductName());
						newProduct.setQuantity(productDto.getQuantity());
						newProduct.setBrand(productDto.getBrand());
						newProduct.setPricePerUnit(productDto.getPricePerUnit());
// generate barcode

						String barCode = RandomStringUtils.randomAlphanumeric(5);

						newProduct.setBarcode(barCode);

						newProduct.setCategory(existingCategory);  // child .set (Parent)


						existingCategory.getProducts().add(newProduct); // add child  list  to the parent

						Product savedProduct = productRepository.save(newProduct);   // save child

						//in many to many mapping there is no child and parent

						newProduct.getVendors().add(existingVendor);  // add product to vendor list // dont forget to initialise in the entity the collection you have created for both

						existingVendor.getProducts().add(newProduct); // add vendor to product list


						vendorRepository.save(existingVendor);

						log.info(" new product  saved  with the vendor   : {}",productDto.getProductName() + existingVendor.getVendorName());
						// save that entity  which has   @ join table
						Purchase purchase = new Purchase();

//						purchase.setProductId(savedProduct.getProductId());
//						purchase.setPurchasedQuantity(productDto.getQuantity());
//						purchase.setVendor(existingVendor);


						purchase.setProduct(savedProduct);
						savedProduct.getPurchases().add(purchase);

						purchase.setVendor(existingVendor);
						existingVendor.getPurchases().add(purchase);

						purchase.setPurchasedQuantity(productDto.getQuantity());
						purchaseRepository.save(purchase);


						ProductTransactionLog productTransactionLog = new ProductTransactionLog();
						productTransactionLog.setQuantityChanged(productDto.getQuantity());
						productTransactionLog.setTransactionType("added");
						productTransactionLog.setProduct(savedProduct);
						savedProduct.getProductTransactionLog().add(productTransactionLog);
						productTransactionLogRepository.save(productTransactionLog);
// response

						ProductDTO productResponseDto = new ProductDTO();
						productResponseDto.setProductId(savedProduct.getProductId());
						productResponseDto.setProductName(savedProduct.getProductName());
						productResponseDto.setQuantity(savedProduct.getQuantity());
						productResponseDto.setBrand(savedProduct.getBrand());
						productResponseDto.setBarcode(savedProduct.getBarcode());

						productResponseDto.setPricePerUnit(savedProduct.getPricePerUnit());
						productResponseDto.setCreatedAt(savedProduct.getCreatedAt());
						productResponseDto.setUpdatedAt(savedProduct.getUpdatedAt());
						productResponseDtos.add(productResponseDto);
					}


				}

			}else {

				throw new ResourceNotFoundException("category  doesn't exist    Enter the correct category    Electronics , Clothing , Books,   Sports, Furniture ");
			}

		}else{

			throw new ResourceNotFoundException("vendor doesn't exist");
		}




		productRequestDTOs.setVendorId(existingVendor.getCustomerId());
		productRequestDTOs.setVendorName(existingVendor.getVendorName());


		CategoryDto categoryResponseDto = new CategoryDto();
		categoryResponseDto.setCategoryId(existingCategory.getCategoryId());
		categoryResponseDto.setCategoryName(existingCategory.getCategoryName());

		productRequestDTOs.setCategory(categoryResponseDto);

		productRequestDTOs.setProducts(productResponseDtos);


		return productRequestDTOs;



	}

	public List<Product> findAllProducts() {
		log.info("findAllProducts method triggered ");
		
		List<Product> allProducts = productRepository.findAll();
		
		log.info(" details added sucesfully");
		return allProducts;
	}

	public List<ProductCategoryCustomised> findProductByCategory(String categoryName) {
		
		log.info("input :{}", categoryName);
		
		List<ProductCategoryCustomised> productByCategoryName = null;

		if (categoryName == null || categoryName.trim().isEmpty() || categoryName.equals("null")) {

			
			validateResponse.setMessage(
					"categoryName  can not be null , enter valid categoryName among  Electronics , Clothing , Books, Sports, Furniture ");
			
           log.error("categoryName  can not be null , enter valid categoryName among  Electronics , Clothing , Books, Sports, Furniture ");
           
           log.debug(categoryName);
           
			productByCategoryName = Collections.emptyList();

		} else {

			productByCategoryName = productRepository.findProductByCategoryName(categoryName);

			System.out.println(productByCategoryName);

			if (productByCategoryName.isEmpty()) {


				Optional<Category> byCategoryName = categoryRepository.findByCategoryName(categoryName);

				if (byCategoryName.isPresent()){

					validateResponse.setMessage("no products are there in the entered category");

					log.info("no products are there in the entered category");

				}  else {

					log.error("No products found for category: " + categoryName
							+ "    Enter the correct  category name among  Electronics , Clothing , Books, Sports, Furniture");
					log.debug(categoryName);


					throw new ResourceNotFoundException("No products found for category: " + categoryName
							+ "   Enter the correct  category name among  Electronics , Clothing , Books, Sports, Furniture");
				}




			}

		
		}
		
		log.info("list fetched sucessfuly");
		return productByCategoryName;
	}

	public Long countProductsByCategoryName(String categoryName) {
//	    1('Electronics'),
//	    2('Clothing'),
//	    3('Books'),
//	    4('Sports'),
//	    5('Furniture')
		log.info("input :{}", categoryName);
		Long countProducts = null;


		if (categoryName == null || categoryName.trim().isEmpty() || categoryName.equals("null")) {

			validateResponse.setMessage(

					"categoryName  can not be null , enter valid categoryName among  Electronics , Clothing , Books, Sports, Furniture ");
			log.error("categoryName  can not be null , enter valid categoryName among  Electronics , Clothing , Books, Sports, Furniture ");

			log.debug(categoryName);



		} else {

// input is valid

			Optional<Category> byCategoryName = categoryRepository.findByCategoryName(categoryName);

			if (byCategoryName.isPresent()){

				countProducts = productRepository.countProductsByCategoryName(categoryName);



			}else {

				// entered category  name is wrong

				log.error("the  products  average can not be determined  by the category name : " + categoryName
						+ "    Enter the correct  category name among  Electronics , Clothing , Books, Sports, Furniture");

				log.debug(categoryName);

				throw new ResourceNotFoundException(
						"the  products  average can not be determined  by the category name : " + categoryName
								+ "    Enter the correct  category name among  Electronics , Clothing , Books, Sports, Furniture");

			}

		}


		log.info("response : {}" ,countProducts);
		return countProducts;
	}

	public Double findAverageProductPriceByCategoryName(String categoryName) {
		log.info("input :{}", categoryName);


		Double averageProductPriceByCategoryName = null;



		if (categoryName == null || categoryName.trim().isEmpty() || categoryName.equals("null")) {

			validateResponse.setMessage(

					"categoryName  can not be null , enter valid categoryName among  Electronics , Clothing , Books, Sports, Furniture ");
			log.error("categoryName  can not be null , enter valid categoryName among  Electronics , Clothing , Books, Sports, Furniture ");
			
	           log.debug(categoryName);
	           


		} else {

// input is valid

				    Category  category = categoryRepository.findByCategoryName(categoryName).
						orElseThrow(() -> new ResourceNotFoundException("the  products  average can not be determined  by the category name : " + categoryName
								+ "    Enter the correct  category name among  Electronics , Clothing , Books, Sports, Furniture"));


		          	averageProductPriceByCategoryName = productRepository
					.findAverageProductPriceByCategoryName(categoryName);

			if (averageProductPriceByCategoryName== null){


				validateResponse.setMessage("no products found for the category ");


			}


			}


		log.info("response : {}" ,averageProductPriceByCategoryName);
		return averageProductPriceByCategoryName;

	}

	public List<ProductCustomised> getProductsPriceInAscOrder() {
		log.info("getProductsPriceInAscOrder method triggered");
		
		List<ProductCustomised> productsInAscOrder = productRepository.getProductsPriceInAscOrder();
		log.info("list fetched sucessfuly");
		return productsInAscOrder;
	}

	public List<ProductCategoryCustomised> countProductsByCategory() {
		log.info("countProductsByCategory method triggered");
		List<ProductCategoryCustomised> countProductsByCategory = productRepository.countProductsByCategory();
		log.info("list fetched sucessfuly");
		return countProductsByCategory;
	}

	public List<ProductCategoryCustomised> categoryProductsSum() {
		log.info("categoryProductsSum method triggered");
		List<ProductCategoryCustomised> countProductsByCategory = productRepository.categoryProductsSum();
		
        log.info("list fetched sucessfuly");
		return countProductsByCategory;
	}

}
