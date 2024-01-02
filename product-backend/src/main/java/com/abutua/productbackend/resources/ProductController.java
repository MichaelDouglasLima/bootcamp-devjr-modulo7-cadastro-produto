package com.abutua.productbackend.resources;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.models.Product;

// import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin
public class ProductController {

    private List<Product> products = Arrays.asList( 
        new Product(1, "Product 01", "Description 01", 1, false, false, 100.50),
        new Product(2, "Product 02", "Description 02", 2, true, true, 200.50),
        new Product(3, "Product 03", "Description 03", 3, false, true, 300.50),
        new Product(4, "Product 04", "Description 04", 4, true, false, 400.50)

    );

    // private List<Product> products = new ArrayList<>();

    // @PostContruct Convoca o Método abaixo, assim que o objeto ProductController é
    // construído
    // @PostConstruct
    // public void init() {
    //     Product p1 = new Product(1, "Product 01", 100.50);
    //     Product p2 = new Product(2, "Product 02", 200.50);
    //     Product p3 = new Product(3, "Product 03", 300.50);

    //     products.add(p1);
    //     products.add(p2);
    //     products.add(p3);
    // }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProdut(@PathVariable int id) {

        // if (id <= products.size()) {
        // return ResponseEntity.ok(products.get(id - 1));
        // }
        // else {
        // // Mensagem de Erro do Navegador
        // // return ResponseEntity.notFound().build();

        // // Mensagem de Erro do seu Servidor
        // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        // }

        // Mensagem de Erro com Programação Funcional
        Product prod =  products.stream()
                                .filter(p -> p.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ResponseEntity.ok(prod);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return products;
    }

}
