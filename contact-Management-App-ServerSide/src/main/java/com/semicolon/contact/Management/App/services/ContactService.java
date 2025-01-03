package com.semicolon.contact.Management.App.services;
import com.semicolon.contact.Management.App.Dto.requests.ContactRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.ContactResponseDTO;
import com.semicolon.contact.Management.App.models.entities.Contact;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ContactService {
    ContactResponseDTO createContact(@Valid ContactRequestDTO contactRequestDTO);
    ContactResponseDTO getContactById(@NotNull Long id);
    Page<Contact> getAllContacts(int pageNo, int pageSize);
    ContactResponseDTO updateContact(@NotNull Long id, @Valid ContactRequestDTO contactRequestDTO);
    void deleteContact(@NotNull Long id);
    ContactResponseDTO uploadPhoto(@NotNull Long id, @NotNull String photoUrl);
    ContactResponseDTO updatePhoto(@NotNull Long id, @NotNull String photoUrl);
}




