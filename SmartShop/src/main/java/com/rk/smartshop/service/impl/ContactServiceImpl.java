package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Contact;
import com.rk.smartshop.repository.ContactRepository;
import com.rk.smartshop.service.ContactService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
  private final ContactRepository contactRepository;

  @Override
  public Contact create(Contact contact) {
    log.info("create contact with date {}", contact);

    Long id = contactRepository.create(contact);
    contact.setId(id);

    return contact;
  }

  @Override
  public Contact update(Long contactId, Contact contact) {
    log.info("update contact with id {}, data {}", contact);

    contactRepository.update(contact, contactId);

    return getById(contactId);
  }

  @Override
  public void delete(Long contactId) {
    log.info("delete contact with id {}" , contactId);

    getById(contactId);
    contactRepository.delete(contactId);
  }

  @Override
  public Contact getById(Long contactId) {
    log.info("get contact by id {}", contactId);

    Optional<Contact> contactOptional = contactRepository.getById(contactId);

    return contactOptional.orElseThrow(() -> new ResourceNotFoundException("Contact not found with id "+contactId));

  }

  @Override
  public List<Contact> getAllContacts() {
    log.info("get all contacts");

    return contactRepository.getAll();
  }
}
