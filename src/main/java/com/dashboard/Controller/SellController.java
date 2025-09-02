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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.dashboard.Model.Sell;
import com.dashboard.Service.SellService;


@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SellController {

    @Autowired
    private SellService sellService;

    @GetMapping
    public List<Sell> getAll(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day) {
            return sellService.getAll(year, month, day);
    }
    
    

    @GetMapping("/{id}")
    public Sell getById(@PathVariable Long id) {
        return sellService.getById(id);
    }

    @PostMapping
    public Sell create(@RequestBody Sell sales) {
        return sellService.save(sales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sell> update(@PathVariable Long id, @RequestBody Sell updated) {
        Sell existing = sellService.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        updated.setProductId(id);
        return ResponseEntity.ok(sellService.save(updated));
    }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) {
         sellService.delete(id);
         return ResponseEntity.ok().build();
     }
     
}


