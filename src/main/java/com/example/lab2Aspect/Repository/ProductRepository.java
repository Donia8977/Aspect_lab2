package com.example.lab2Aspect.Repository;

import com.example.lab2Aspect.models.Product;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {

}
