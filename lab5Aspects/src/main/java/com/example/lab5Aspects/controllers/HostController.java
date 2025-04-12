package com.example.lab5Aspects.controllers;

import com.example.lab5Aspects.Repository.PropertyRepository;
import com.example.lab5Aspects.models.Host;
import com.example.lab5Aspects.models.Property;
import com.example.lab5Aspects.services.HostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private HostService hostService;
    @Autowired
    private PropertyRepository propertyRepository;

    @PostMapping
    public ResponseEntity<Host> createHost(@Valid @RequestBody Host host) {
        return new ResponseEntity<>(hostService.createHost(host), HttpStatus.CREATED);
    }

    @PostMapping("/{hostId}/properties")
    public ResponseEntity<Property> addPropertyToHost(@PathVariable Long hostId, @Valid @RequestBody Property propertyRequest) {
        Host host = hostService.getHostById(hostId).orElseThrow(() -> new RuntimeException("Host not found"));
        propertyRequest.setHost(host);
        Property savedProperty = propertyRepository.save(propertyRequest);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    @GetMapping("/{hostId}/properties")
    public ResponseEntity<List<Property>> getAllPropertiesForHost(@PathVariable Long hostId) {
        Host host = hostService.getHostById(hostId)
                .orElseThrow(() -> new RuntimeException("Host not found"));

        List<Property> properties = propertyRepository.findAllByHostId(hostId);

        return ResponseEntity.ok(properties);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Host> getHost(@PathVariable Long id) {
        return hostService.getHostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Host> updateHost(@PathVariable Long id, @Valid @RequestBody Host host) {
        return ResponseEntity.ok(hostService.updateHost(id, host));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        hostService.deleteHost(id);
        return ResponseEntity.noContent().build();
    }

}
