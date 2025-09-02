package com.dashboard.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dashboard.Model.Sell;
import com.dashboard.Repository.SellRepository;


@Service
public class SellService {
    

    @Autowired
    private SellRepository sellRepository;

    
    public List<Sell> getAll(Integer year, Integer month, Integer day){
        return sellRepository.findSalesByDate(year, month, day);
    }

    public Sell getById(Long id){
        return sellRepository.findById(id).orElse(null);
    }


    public Sell save(Sell sales){
        return sellRepository.save(sales);
    }

    public void delete(Long id) {
        sellRepository.deleteById(id);
    }

}
