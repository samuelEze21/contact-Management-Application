package com.semicolon.contact.Management.App.controller;

import com.semicolon.contact.Management.App.Dto.requests.ContactRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.ContactResponseDTO;
import com.semicolon.contact.Management.App.Exception.ContactNotFoundException;
import com.semicolon.contact.Management.App.models.entities.Contact;
import com.semicolon.contact.Management.App.models.repositories.ContactRepository;
import com.semicolon.contact.Management.App.services.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;


    @PostMapping("/register-contact")
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody ContactRequestDTO contactRequestDTO) {
        ContactResponseDTO responseDTO = contactService.createContact(contactRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/getContact/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable
                                                                 @NotNull
                                                                 Long id) {
        ContactResponseDTO responseDTO = contactService.getContactById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<Page<Contact>> getAllContacts( @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Contact> contactsPage = contactService.getAllContacts(pageNo, pageSize);
        return new ResponseEntity<>(contactsPage, HttpStatus.OK);
    }


    @PutMapping("/updateContact/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(@PathVariable @NotNull Long id,
                                                            @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        ContactResponseDTO responseDTO = contactService.updateContact(id, contactRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/deleteContactById{id}")
    public ResponseEntity<ContactResponseDTO> deleteContactById(@PathVariable @NotNull Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload-Photo/{id}")
    public ResponseEntity<ContactResponseDTO> uploadPhoto(
            @PathVariable @NotNull Long id,
            @RequestParam("photoUrl") @NotNull @Pattern(regexp = "^(http|https)://.*$") String photoUrl) {
        ContactResponseDTO responseDTO = contactService.uploadPhoto(id, photoUrl);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updatePhoto/{id}")
    public ResponseEntity<ContactResponseDTO> updatePhoto(
            @PathVariable @NotNull Long id,
            @RequestParam("photoUrl") @NotNull @Pattern(regexp = "^(http|https)://.*$") String photoUrl) {
        ContactResponseDTO responseDTO = contactService.updatePhoto(id, photoUrl);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/getPhoto/{id}")
    public ResponseEntity<String> getPhoto(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + id));

        String photoUrl = contact.getPhotoUrl();
        if (photoUrl == null) {
            return new ResponseEntity<>("No photo uploaded", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(photoUrl, HttpStatus.OK);
    }

}




