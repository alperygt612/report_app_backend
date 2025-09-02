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

import com.dashboard.Model.Manufacture;
import com.dashboard.Service.ManufactureService;

@RestController
@RequestMapping("/api/production")
@CrossOrigin("*")
public class ManufactureController {
    
    @Autowired
    private ManufactureService manufactureService;


    @GetMapping
    public ResponseEntity<List<Manufacture>> getAll(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day) {
        List<Manufacture> productions = manufactureService.getAll(year, month, day);
        return ResponseEntity.ok(productions);
    }

    @GetMapping("/{id}")
    public Manufacture getById(@PathVariable Long id) {
        return manufactureService.getById(id);
    }

    @PostMapping
    public Manufacture create(@RequestBody Manufacture production) {
        return manufactureService.save(production);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacture> update(@PathVariable Long id, @RequestBody Manufacture updated) {
        Manufacture existing = manufactureService.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        updated.setProductionId(id);
        return ResponseEntity.ok(manufactureService.save(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        manufactureService.delete(id);
        return ResponseEntity.ok().build();
    }
}
