package com.ims.dao;

import com.ims.dto.ProductCustomerVendorReportDTO;
import com.ims.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Product, Long> {




    @Query(value = "SELECT p.product_name, p.brand, o.ordered_quantity, pu.purchased_quantity, " +
            "c.customer_name, v.vendor_name " +
            "FROM products p " +
            "INNER JOIN orders o ON p.product_id = o.product_id " +
            "INNER JOIN purchases pu ON p.product_id = pu.product_id " +
            "INNER JOIN customer_product cp ON p.product_id = cp.product_id " +
            "INNER JOIN customers c ON cp.customer_id = c.customer_id " +
            "INNER JOIN vendor_product vp ON p.product_id = vp.product_id " +
            "INNER JOIN vendor v ON vp.vendor_id = v.vendor_id " +
            "WHERE p.product_id = (" +
            "    SELECT p2.product_id " +
            "    FROM products p2 " +
            "    WHERE p2.product_name = :productName " +
            "    AND p2.brand = :brand" +
            ")", nativeQuery = true)
    List<Object[]> findProductCustomerVendorReport(@Param("productName") String productName,
                                                   @Param("brand") String brand);








    @Query(value = "SELECT ptl.transaction_id, ptl.product_id, ptl.created_at, ptl.quantity_changed, " +
            "ptl.transaction_type, p.barcode, p.brand,  " +
            "p.price_per_unit, p.product_name, p.quantity, p.updated_at, p.category_id " +
            "FROM product_transaction_log ptl " +
            "INNER JOIN products p ON ptl.product_id = p.product_id " +
            "WHERE p.product_name = :productName AND p.brand = :brand",
            nativeQuery = true)
    List<Object[]> findProductTransactionLogsByProductNameAndBrand(@Param("productName") String productName,
                                                                   @Param("brand") String brand);
}




