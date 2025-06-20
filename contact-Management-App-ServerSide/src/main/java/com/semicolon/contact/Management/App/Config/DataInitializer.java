package com.semicolon.contact.Management.App.config;

import com.semicolon.contact.Management.App.models.entities.AppRole;
import com.semicolon.contact.Management.App.models.entities.Role;
import com.semicolon.contact.Management.App.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        // Initialize default roles if they don't exist
        for (AppRole appRole : AppRole.values()) {
            if (roleRepository.findByName(appRole).isEmpty()) {
                Role role = new Role(appRole);
                roleRepository.save(role);
            }
        }
    }
}