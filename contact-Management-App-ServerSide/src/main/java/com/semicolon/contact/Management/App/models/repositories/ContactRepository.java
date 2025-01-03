package com.semicolon.contact.Management.App.models.repositories;
import com.semicolon.contact.Management.App.models.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    Optional<Contact> findById(Long id);



}
