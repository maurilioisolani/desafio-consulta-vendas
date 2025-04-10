package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleForSellerDTO;
import com.devsuperior.dsmeta.dto.SaleFullDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleForSellerDTO(seller.name, SUM(sale.amount)) " +
            "FROM Sale sale " +
            "JOIN sale.seller seller " +
            "WHERE sale.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY seller.name")
    List<SaleForSellerDTO> findSalesSummaryByDate(@Param("minDate") LocalDate minDate,
                                                  @Param("maxDate") LocalDate maxDate);


    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleFullDTO(s.id, s.amount, s.date, s.seller.name) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "AND LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<SaleFullDTO> findSalesReportByDateAndName(@Param("minDate") LocalDate minDate,
                                                   @Param("maxDate") LocalDate maxDate,
                                                   @Param("name") String name,
                                                   Pageable pageable);

}
