    package com.semicolon.contact.Management.App.ComponentsTest;
    import com.semicolon.contact.Management.App.Dto.requests.ContactRequestDTO;
    import com.semicolon.contact.Management.App.Dto.responses.ContactResponseDTO;
    import com.semicolon.contact.Management.App.Exception.ContactNotFoundException;
    import com.semicolon.contact.Management.App.models.entities.Contact;
    import com.semicolon.contact.Management.App.models.repositories.ContactRepository;
    import com.semicolon.contact.Management.App.services.ContactService;
    import jakarta.validation.ConstraintViolationException; // Use this import
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.data.domain.Page;

    import java.util.Optional;
    import static org.junit.jupiter.api.Assertions.*;

    @SpringBootTest
    public class ContactServiceImplTest {

        @Autowired
        private ContactService contactService;

        @Autowired
        private ContactRepository contactRepository;

        @BeforeEach
        void setUp() {
            contactRepository.deleteAll();
        }

        @Test
        void test_user_can_add_create_new_contact() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("Stan");
            contactRequestDTO.setLastName("Loski");
            contactRequestDTO.setEmail("StanLoski5@gmail.com");
            contactRequestDTO.setPhoneNumber("+234 701 566 7821");
            contactRequestDTO.setAddress("121 sabo way, Yaba, Lagos");
            contactRequestDTO.setStatus("Married");
            contactRequestDTO.setTitle("Doc");
            contactRequestDTO.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/6/66/Albert_Einstein_1921_by_F_Schmutzer_-_restoration.jpg");
            ContactResponseDTO contactResponseDTO = contactService.createContact(contactRequestDTO);

            assertNotNull(contactResponseDTO);
            assertNotNull(contactResponseDTO.getId());
            assertNotNull(contactResponseDTO.getPhotoUrl());
            assertEquals(contactRequestDTO.getFirstName(), contactResponseDTO.getFirstName());
            assertEquals(contactRequestDTO.getLastName(), contactResponseDTO.getLastName());
            assertEquals(contactRequestDTO.getEmail(), contactResponseDTO.getEmail());
            assertEquals(contactRequestDTO.getPhoneNumber(), contactResponseDTO.getPhoneNumber());
            assertEquals(contactRequestDTO.getAddress(), contactResponseDTO.getAddress());
            assertEquals(contactRequestDTO.getStatus(), contactResponseDTO.getStatus());
            assertEquals(contactRequestDTO.getTitle(), contactResponseDTO.getTitle());
            assertEquals(contactRequestDTO.getPhotoUrl(), contactResponseDTO.getPhotoUrl());
            assertEquals("Contact Added", contactResponseDTO.getContactSuccessMessage());
            assertEquals(1, contactRepository.count());
        }

        @Test
        void test_that_user_can_Read_Contact_Added_by_Id() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("Stan");
            contactRequestDTO.setLastName("Loski");
            contactRequestDTO.setEmail("StanLoski5@gmail.com");
            contactRequestDTO.setPhoneNumber("+234 701 566 7821");
            contactRequestDTO.setAddress("121 sabo way, Yaba, Lagos");
            contactRequestDTO.setStatus("Married");
            contactRequestDTO.setTitle("Doc");
            contactRequestDTO.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/6/66/Albert_Einstein_1921_by_F_Schmutzer_-_restoration.jpg");
            ContactResponseDTO createdContactResponse = contactService.createContact(contactRequestDTO);

            ContactResponseDTO foundContact = contactService.getContactById(createdContactResponse.getId());
            assertNotNull(foundContact);
            assertEquals(createdContactResponse.getId(), foundContact.getId());
        }

        @Test
        void test_that_user_can_readAll_getAll_contacts_added() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("Stan");
            contactRequestDTO.setLastName("Loski");
            contactRequestDTO.setEmail("StanLoski5@gmail.com");
            contactRequestDTO.setPhoneNumber("+234 701 566 7821");
            contactRequestDTO.setAddress("121 sabo way, Yaba, Lagos");
            contactRequestDTO.setStatus("Married");
            contactRequestDTO.setTitle("Doc");
            contactRequestDTO.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/6/66/Albert_Einstein_1921_by_F_Schmutzer_-_restoration.jpg");
            ContactResponseDTO responseDTO = contactService.createContact(contactRequestDTO);
            assertNotNull(responseDTO);

            ContactRequestDTO contact2Request = new ContactRequestDTO();
            contact2Request.setFirstName("Joy");
            contact2Request.setLastName("Johnson");
            contact2Request.setEmail("johnson@gmail.com");
            contact2Request.setPhoneNumber("+234 701 007 9987");
            contact2Request.setAddress("16 sabo way, Onipan, Lagos");
            contact2Request.setStatus("Active");
            contact2Request.setTitle("Trader");
            contact2Request.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/6/66/Albert_Einstein_1921_by_F_Schmutzer_-_restoration.jpg");
            ContactResponseDTO responseDTO2 = contactService.createContact(contact2Request);
            assertNotNull(responseDTO2);
            assertEquals(2, contactRepository.count());

            // Fetch paginated contacts
            Page<Contact> contactsPage = contactService.getAllContacts(0, 10);
            assertEquals(2, contactsPage.getTotalElements());
            assertEquals(1, contactsPage.getTotalPages());
            assertEquals("Stan", contactsPage.getContent().get(0).getFirstName());
        }


        @Test
        void test_that_user_can_update_contact() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("Stan");
            contactRequestDTO.setLastName("Loski");
            contactRequestDTO.setEmail("StanLoski5@gmail.com");
            contactRequestDTO.setPhoneNumber("+234 701 566 7821");
            contactRequestDTO.setAddress("121 sabo way, Yaba, Lagos");
            contactRequestDTO.setStatus("Married");
            contactRequestDTO.setTitle("Doc");
            contactRequestDTO.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/6/66/Albert_Einstein_1921_by_F_Schmutzer_-_restoration.jpg");
            ContactResponseDTO responseDTO = contactService.createContact(contactRequestDTO);
            assertNotNull(responseDTO);
            assertNotNull(responseDTO.getId());

            ContactRequestDTO updatedContactRequestDTO = new ContactRequestDTO();
            updatedContactRequestDTO.setFirstName("Stanley");
            updatedContactRequestDTO.setLastName("Loco");
            updatedContactRequestDTO.setPhoneNumber("+234 701 566 7834");
            updatedContactRequestDTO.setEmail("stanfrontend1@example.com");
            ContactResponseDTO updateContactDTO = contactService.updateContact(responseDTO.getId(), updatedContactRequestDTO);
            assertNotNull(updateContactDTO);
            assertEquals(responseDTO.getId(), updateContactDTO.getId());
            assertEquals(updatedContactRequestDTO.getFirstName(), updateContactDTO.getFirstName());
            assertEquals(updatedContactRequestDTO.getLastName(), updateContactDTO.getLastName());
            assertEquals(updatedContactRequestDTO.getEmail(), updateContactDTO.getEmail());
            assertEquals(updatedContactRequestDTO.getPhoneNumber(), updateContactDTO.getPhoneNumber());
        }

        @Test
        void test_that_user_can_delete_contact() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("Mary");
            contactRequestDTO.setLastName("Johnson");
            contactRequestDTO.setEmail("johnson@gmail.com");
            contactRequestDTO.setPhoneNumber("+234 701 566 7821");
            contactRequestDTO.setAddress("16 sabo way, Onipan, Lagos");
            contactRequestDTO.setStatus("Active");
            contactRequestDTO.setTitle("Trader");
            ContactResponseDTO responseDTO = contactService.createContact(contactRequestDTO);
            assertEquals(1, contactRepository.count());
            assertNotNull(responseDTO);
            assertNotNull(responseDTO.getId());
            contactService.deleteContact(responseDTO.getId());
            assertEquals(0, contactRepository.count());
        }


        @Test
        void test_that_user_can_upload_a_picture_for_contact() {
            Contact contact = new Contact();
            contact.setFirstName("Miracle");
            contact.setLastName("Hadobo");
            contact.setEmail("miracale66@example.com");
            contact.setPhoneNumber("233 7010720076");
            contact.setAddress("12 Bariga boy road");
            contact.setStatus("Active");
            contact.setTitle("Fullstack Boy");
            contact.setPhotoUrl("https://example.com/ronaldo.jpg");
            Contact savedContact = contactRepository.save(contact);

            String uploadedPhotoUrl = "https://example.com/new-photo.jpg";
            ContactResponseDTO responseDTO = contactService.uploadPhoto(savedContact.getId(), uploadedPhotoUrl);
            assertNotNull(responseDTO);
            assertEquals("Photo Uploaded Successfully", responseDTO.getPhotoSuccessMessage());

            Optional<Contact> updatedContact = contactRepository.findById(savedContact.getId());
            assertTrue(updatedContact.isPresent());
            assertEquals(uploadedPhotoUrl, updatedContact.get().getPhotoUrl());
            assertEquals(1, contactRepository.count());
        }

        @Test
        void test_that_user_can_update_photo_for_contact() {
            Contact contact = new Contact();
            contact.setFirstName("Miracle");
            contact.setLastName("Hadobo");
            contact.setEmail("miracale66@example.com");
            contact.setPhoneNumber("233 7010720076");
            contact.setAddress("12 Bariga boy road");
            contact.setStatus("Active");
            contact.setTitle("Fullstack Boy");
            String initialPhotoUrl = "https://upload.wikimedia.org/wikipedia/commons/6/66/Albert_Einstein_1921_by_F_Schmutzer_-_restoration.jpg";
            contact.setPhotoUrl(initialPhotoUrl);
            Contact savedContact = contactRepository.save(contact);

            String updatedPhotoUrl = "https://example.com/updated-photo.jpg";
            ContactResponseDTO responseDTO = contactService.updatePhoto(savedContact.getId(), updatedPhotoUrl);
            assertNotNull(responseDTO);
            assertEquals("Photo Uploaded Successfully", responseDTO.getPhotoSuccessMessage());

            Optional<Contact> updatedContact = contactRepository.findById(savedContact.getId());
            assertTrue(updatedContact.isPresent());
            assertEquals(updatedPhotoUrl, updatedContact.get().getPhotoUrl());
            assertEquals(1, contactRepository.count());
        }


        @Test
        void test_user_creating_contact_with_null_fields_throws_exception() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            assertThrows(ConstraintViolationException.class, () -> {
                contactService.createContact(contactRequestDTO);
            });
        }

        @Test
        void test_CreateContact_with_blank_fields_throws_exception() {
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("");
            contactRequestDTO.setLastName("");
            contactRequestDTO.setEmail("");
            contactRequestDTO.setPhoneNumber("");
            contactRequestDTO.setAddress("");
            contactRequestDTO.setStatus("");
            contactRequestDTO.setTitle("");
            assertThrows(ConstraintViolationException.class, () -> {
                contactService.createContact(contactRequestDTO);
            });

        }

        @Test
        void test_getting_contact_by_id_Throws_not_foundException_for_nonExisting_contact() {
            Contact contact = new Contact();
            contact.setId(99L);
            assertThrows(ContactNotFoundException.class, () -> {
                contactService.getContactById(contact.getId());
                String expectedMessage = "Contact with id 99 does not exist";
                String actualMessage = contactService.getContactById(99L).getErrorMessage();
                assertEquals(expectedMessage, actualMessage);
            });
        }

        @Test
        void test_updating_Contact_Not_found_exception() {
            Long id = 999L;
            ContactRequestDTO contactRequestDTO = new ContactRequestDTO();
            contactRequestDTO.setFirstName("Miracle");
            contactRequestDTO.setLastName("Hadobo");
            contactRequestDTO.setEmail("miracale66@example.com");
            contactRequestDTO.setPhoneNumber("233 7010720076");
            assertThrows(ContactNotFoundException.class, () -> {
                contactService.updateContact(id, contactRequestDTO);

                String expectedMessage = "Contact not found with id 999:";
                String actualMessage = contactService.getContactById(id).getErrorMessage();
                assertEquals(expectedMessage, actualMessage);
            });
        }

        @Test
        void test_deleting_contact_Not_found_Exception() {
            Long id = 999L;
            ContactNotFoundException exception = assertThrows(ContactNotFoundException.class, () -> {
                contactService.deleteContact(id);
            });
            String expectedMessage = "Contact not found with id:" + id;
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }

    }