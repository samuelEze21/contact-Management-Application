package com.semicolon.contact.Management.App.repositories;

import com.semicolon.contact.Management.App.models.entities.Role;
import com.semicolon.contact.Management.App.models.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(AppRole name);
}