package com.semicolon.contact.Management.App.utils;

import com.semicolon.contact.Management.App.Dto.requests.ContactRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.ContactResponseDTO;
import com.semicolon.contact.Management.App.models.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact toContactEntity(ContactRequestDTO dto) {
        Contact contact = new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setEmail(dto.getEmail());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setAddress(dto.getAddress());
        contact.setTitle(dto.getTitle());
        contact.setStatus(dto.getStatus());
        contact.setPhotoUrl(dto.getPhotoUrl()); // Map photoUrl
        return contact;
    }

    public ContactResponseDTO toContactResponseDTO(Contact contact) {
        ContactResponseDTO dto = new ContactResponseDTO();
        dto.setId(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setEmail(contact.getEmail());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setAddress(contact.getAddress());
        dto.setTitle(contact.getTitle());
        dto.setStatus(contact.getStatus());
        dto.setPhotoUrl(contact.getPhotoUrl()); // Map photoUrl
        dto.setContactSuccessMessage("Contact Added");
        dto.setPhotoSuccessMessage("Photo Uploaded Successfully");
        return dto;
    }

}
