package com.example.lab5Aspects.services;

import com.example.lab5Aspects.Repository.HostRepository;
import com.example.lab5Aspects.Repository.PropertyRepository;
import com.example.lab5Aspects.models.Host;
import com.example.lab5Aspects.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> getPropertById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        Property existingProperty = propertyRepository.findById(id).orElseThrow();
        existingProperty.setLocation(updatedProperty.getLocation());
        existingProperty.setTitle(updatedProperty.getTitle());
        existingProperty.setPricePerNight(updatedProperty.getPricePerNight());
        return propertyRepository.save(existingProperty);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

}
