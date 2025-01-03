package com.semicolon.contact.Management.App.services;

import com.semicolon.contact.Management.App.Dto.requests.ContactRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.ContactResponseDTO;
import com.semicolon.contact.Management.App.Exception.ContactNotFoundException;
import com.semicolon.contact.Management.App.models.entities.Contact;
import com.semicolon.contact.Management.App.models.repositories.ContactRepository;
import com.semicolon.contact.Management.App.utils.ContactMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }



    @Override
    public ContactResponseDTO createContact(@Valid ContactRequestDTO contactRequestDTO) {
        Contact contact = contactMapper.toContactEntity(contactRequestDTO);
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.toContactResponseDTO(savedContact);
    }


    @Override
    public ContactResponseDTO getContactById(@NotNull Long id) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            return contactMapper.toContactResponseDTO(existingContact.get());
        } else {
            throw new ContactNotFoundException("Contact not found with id:" + id);
        }
    }


    @Override
    public Page<Contact> getAllContacts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("firstName").descending());
        return contactRepository.findAll(pageable);
    }


    @Override
    public ContactResponseDTO updateContact(@NotNull Long id, @Valid ContactRequestDTO contactRequestDTO) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();
            contact.setFirstName(contactRequestDTO.getFirstName());
            contact.setLastName(contactRequestDTO.getLastName());
            contact.setEmail(contactRequestDTO.getEmail());
            contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
            contact.setAddress(contactRequestDTO.getAddress());
            contact.setTitle(contactRequestDTO.getTitle());
            contact.setPhotoUrl(contactRequestDTO.getPhotoUrl());
            Contact savedContact = contactRepository.save(contact);
            return contactMapper.toContactResponseDTO(savedContact);
        } else {
            throw new ContactNotFoundException("Contact not found with id:" + id);
        }
    }


    @Override
    public void deleteContact(@NotNull Long id) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            contactRepository.deleteById(id);
        } else {
            throw new ContactNotFoundException("Contact not found with id:" + id);
        }
    }


    @Override
    public ContactResponseDTO uploadPhoto(@NotNull Long id, @NotNull String photoUrl) {
        Optional<Contact> existingContact = contactRepository.findById(id);

        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();
            contact.setPhotoUrl(photoUrl);
            Contact updatedContact = contactRepository.save(contact);

            ContactResponseDTO contactResponseDTO = contactMapper.toContactResponseDTO(updatedContact);
            contactResponseDTO.setPhotoUrl(photoUrl);
            contactResponseDTO.setPhotoSuccessMessage("Photo Uploaded Successfully");

            return contactResponseDTO;  // map or return the updated response DTO with the success message
        } else {
            throw new ContactNotFoundException("Contact not found with id: " + id);
        }
    }


    @Override
    public ContactResponseDTO updatePhoto(@NotNull Long id, @NotNull String photoUrl) {
        Optional<Contact> savedContact = contactRepository.findById(id);

        if (savedContact.isPresent()) {
            Contact contact = savedContact.get();
            contact.setPhotoUrl(photoUrl);
            Contact updatedContact = contactRepository.save(contact);

            return contactMapper.toContactResponseDTO(updatedContact);
        } else {
            throw new ContactNotFoundException("Contact not found with id: " + id);
        }
    }


}

