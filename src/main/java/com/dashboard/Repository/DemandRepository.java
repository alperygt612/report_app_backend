package com.dashboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.Model.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long>{
    
}
