package com.dashboard.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.dashboard.Model.Sell;
@Repository
public interface SellRepository extends JpaRepository<Sell,Long> {
    @Query("SELECT s FROM Sell s WHERE " +
           "(:year IS NULL OR YEAR(s.salesDate) = :year) AND " +
           "(:month IS NULL OR MONTH(s.salesDate) = :month) AND " +
           "(:day IS NULL OR DAY(s.salesDate) = :day)")
    List<Sell> findSalesByDate(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day);
    
}