package com.semicolon.contact.Management.App.models.repositories;

import com.semicolon.contact.Management.App.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
