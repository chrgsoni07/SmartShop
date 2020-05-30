package com.rk.smartshop.service;

import com.rk.smartshop.model.Contact;
import java.util.List;

public interface ContactService {

  Contact create(Contact contact);

  Contact update(Long contactId, Contact contact);

  void delete(Long contactId);

  Contact getById(Long contactId);

  List<Contact> getAllContacts();
}
