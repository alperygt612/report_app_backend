package com.dashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.Model.stuff;
import com.dashboard.Service.stuffService;

@RestController
@RequestMapping("/api/raw-materials")
@CrossOrigin("*")
public class stuffController {
    
    @Autowired
    private stuffService stuffService;

    @GetMapping
    public List<stuff> getAll(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day) {
        return stuffService.getAll(year, month, day);
    }

    @GetMapping("/{id}")
    public stuff getById(@PathVariable Long id) {
        return stuffService.getById(id);
    }

    @PostMapping
    public stuff create(@RequestBody stuff rawMaterial) {
        return stuffService.save(rawMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<stuff> update(@PathVariable Long id, @RequestBody stuff updated) {
        stuff existing = stuffService.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        updated.setMaterialsId(id);
        return ResponseEntity.ok(stuffService.save(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stuffService.delete(id);
        return ResponseEntity.ok().build();
    }
} 