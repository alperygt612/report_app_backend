package com.dashboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.Model.stuff;
import com.dashboard.Repository.stuffRepository;

@Service
public class stuffService {

    @Autowired
    private stuffRepository stuffRepository;

    public List<stuff> getAll(Integer year, Integer month, Integer day) {
        return stuffRepository.findStuffByDate(year, month, day);
    }

    public stuff getById(Long id) {
        return stuffRepository.findById(id).orElse(null);
    }

    public stuff save(stuff rawMaterial) {
        return stuffRepository.save(rawMaterial);
    }

    public void delete(Long id) {
        stuffRepository.deleteById(id);
    }
} 