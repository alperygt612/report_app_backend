package com.dashboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.Model.Manufacture;
import com.dashboard.Repository.ManufactureRepository;

@Service
public class ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;


    public List<Manufacture> getAll(Integer year, Integer month, Integer day) {
        return manufactureRepository.findManufactureByDate(year, month, day);
    }

    public Manufacture getById(Long id) {
        return manufactureRepository.findById(id).orElse(null);
    }


    public Manufacture save(Manufacture production) {
        return manufactureRepository.save(production);
    }



    public void delete(Long id) {
        manufactureRepository.deleteById(id);
    }
}
