package com.dashboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dashboard.Model.Manufacture;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {

    @Query("SELECT m FROM Manufacture m WHERE " +
           "(:year IS NULL OR YEAR(m.productionDate) = :year) AND " +
           "(:month IS NULL OR MONTH(m.productionDate) = :month) AND " +
           "(:day IS NULL OR DAY(m.productionDate) = :day)")
    List<Manufacture> findManufactureByDate(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day);

    
}
