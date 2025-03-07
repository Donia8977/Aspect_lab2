package com.example.lab2Aspect.controllers;


import com.example.lab2Aspect.Repository.ProductRepository;
import com.example.lab2Aspect.models.Product;
import com.example.lab2Aspect.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Product> CreateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> GetProduct(@PathVariable Long id){
        return productService.getProductById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> UpdateProduct(@PathVariable Long id , @RequestBody Product updatedProduct){
        return productService.updateProduct(id , updatedProduct)
               .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteProduct(@PathVariable Long id){

        return productService.deleteProduct(id)
                ? ResponseEntity.ok("Product has deleted successfully")
                : ResponseEntity.notFound().build();

    }


}
