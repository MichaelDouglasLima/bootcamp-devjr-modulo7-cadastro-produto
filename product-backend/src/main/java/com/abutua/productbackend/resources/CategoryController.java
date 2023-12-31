package com.abutua.productbackend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.models.Category;

@RestController
public class CategoryController {
    
    private List<Category> categorys = Arrays.asList(   new Category(1, "Produção Própria"),
                                                        new Category(2, "Nacional"),
                                                        new Category(3, "Importado"));

    @GetMapping("categorys/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id){
        
        Category cat = categorys.stream()
                                .filter(p -> p.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return ResponseEntity.ok(cat);
    }

    @GetMapping("categorys")
    public List<Category> getCategorys(){
        return categorys;
    }
}
