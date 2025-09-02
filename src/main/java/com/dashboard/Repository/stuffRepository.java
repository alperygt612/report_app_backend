package com.dashboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dashboard.Model.stuff;

@Repository
public interface stuffRepository extends JpaRepository<stuff, Long> {
    
    @Query("SELECT s FROM stuff s WHERE " +
           "(:year IS NULL OR YEAR(s.lastOrderDate) = :year) AND " +
           "(:month IS NULL OR MONTH(s.lastOrderDate) = :month) AND " +
           "(:day IS NULL OR DAY(s.lastOrderDate) = :day)")
    List<stuff> findStuffByDate(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day);

} 