package com.example.lab5Aspects.Repository;

import com.example.lab5Aspects.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property ,Long> {
    List<Property> findAllByHostId(Long hostId);
}
