package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.Contact;
import com.rk.smartshop.service.ContactService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_VERSION + "/contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

  private final ContactService contactService;

  @ApiOperation(value = "Save new contact.", response = Contact.class)
  @PostMapping
  public Contact createContact(@RequestBody Contact contact) {

    log.debug("Create contact {}.", contact);

    return contactService.create(contact);
  }

  @ApiOperation(value = "Update contact.", response = Contact.class)
  @PutMapping("/{contactId}")
  public Contact updateContact(@PathVariable("contactId") Long contactId, @RequestBody Contact contact) {
    log.debug("Create contact with id {}.", contactId);

    return contactService.update(contactId, contact);
  }

  @ApiOperation(value = "Delete contact by id.", response = ResponseEntity.class)
  @DeleteMapping("/{contactId}")
  public ResponseEntity<?> deleteContact(@PathVariable(value = "contactId") Long contactId) {
    log.debug("Delete contact by id {}.", contactId);

    contactService.delete(contactId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get contact by contact id", response = Contact.class)
  @GetMapping("/{contactId}")
  public Contact getContact(@PathVariable("contactId") Long contactId) {

    return contactService.getById(contactId);
  }

  @ApiOperation(value = "Get all contacts", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<Contact> getAllContacts() {

    return contactService.getAllContacts();
  }

}
