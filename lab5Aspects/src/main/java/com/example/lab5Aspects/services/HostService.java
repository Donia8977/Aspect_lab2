package com.example.lab5Aspects.services;

import com.example.lab5Aspects.Repository.HostRepository;
import com.example.lab5Aspects.models.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;

    public Host createHost(Host host) {
        return hostRepository.save(host);
    }

    public Optional<Host> getHostById(Long id) {
        return hostRepository.findById(id);
    }

    public Host updateHost(Long id, Host updatedHost) {
        Host existingHost = hostRepository.findById(id).orElseThrow();
        existingHost.setName(updatedHost.getName());
        existingHost.setEmail(updatedHost.getEmail());
        return hostRepository.save(existingHost);
    }

    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
    }
}
