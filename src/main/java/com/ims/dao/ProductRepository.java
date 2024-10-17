package com.ims.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	// 1. operator : like //findProductByName

	@Query(value = "select p from Product p where p.productName LIKE %:name1%") // search for records contains name1 in
																				// the product name
	List<Product> findProductByName(@Param("name1") String productName);

	
	
	// 2.findProductByCategory
	@Query(value = "SELECT p.productName as productName , c.categoryName as categoryName " + "FROM Product p "
			+ "JOIN p.category c " + "WHERE c.categoryName = :categoryName")
	List<ProductCategoryCustomised> findProductByCategoryName(@Param("categoryName") String categoryName);

	
	
	// 3.countProductsByCategory (using aggregate function count)
	@Query(value = "SELECT COUNT(p)  " + "FROM Product p " + "JOIN p.category c "
			+ "WHERE c.categoryName = :categoryName")
	Long countProductsByCategoryName(@Param("categoryName") String categoryName);

	
	
	
	// 4.findAverageProductPriceByCategory (using aggregate function average)
	@Query("SELECT AVG(p.pricePerUnit) " + "FROM Product p " + "JOIN p.category c "
			+ "WHERE c.categoryName = :categoryName")
	Double findAverageProductPriceByCategoryName(@Param("categoryName") String categoryName);



	// 5.getProductsPriceInAscOrder (order by)
	@Query(value = "SELECT p.productName as productName, p.pricePerUnit as price FROM Product p ORDER BY p.pricePerUnit ASC")
	List<ProductCustomised> getProductsPriceInAscOrder();

	
	
	
	
	// 6.countProductsByCategory (group by )
	@Query("SELECT c.categoryName as categoryName, COUNT(p.productId) as totalProducts " + "FROM Product p "
			+ "JOIN p.category c " + "GROUP BY c.categoryName")
	List<ProductCategoryCustomised> countProductsByCategory();

	
	
	// 7. categories having sum of product price > 5000 grouped by category (having
	// and group by clause)
	@Query("SELECT c.categoryName as categoryName, SUM(p.pricePerUnit) as productAmount " + "FROM Product p "
			+ "JOIN p.category c " + "GROUP BY c.categoryName " + "HAVING SUM(p.pricePerUnit) > 5000")
	List<ProductCategoryCustomised> categoryProductsSum();


	
	// 8. named query
	List<Product> findAllProductByNameDescending();

	
	
	
	// 9 ProductAssociatedTovendors	 ---> left / right 
	@Query(value = "select p.productName AS productName, v.vendorName AS vendorName " + 
		    "from Product p " + 
		    "inner join p.vendors v")
		List<ProductCustomised> findProductAssociatedTovendors();

	

	
	
	// 10. product - vendor - category inner joining three tables
	@Query(value = "select p.productName AS productName, v.vendorName AS vendorName, c.categoryName AS category "
			+ "from Product p " + "inner join p.vendors v " + "inner join v.products vp " + "inner join p.category c " + // entity
			"where vp.productId = p.productId")
	List<ProductCustomised> findProductAssociatedTovendorsWithCategory();



	Optional<Product> findByProductNameAndBrand(String productName,String brand);
}
