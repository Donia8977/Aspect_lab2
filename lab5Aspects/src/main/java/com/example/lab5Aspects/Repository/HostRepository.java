package com.example.lab5Aspects.Repository;

import com.example.lab5Aspects.models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface HostRepository extends JpaRepository <Host, Long> {}
