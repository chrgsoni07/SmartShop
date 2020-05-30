package com.rk.smartshop.mapper;

import com.rk.smartshop.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper implements RowMapper<Contact> {

  @Override
  public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
    Contact contact = new Contact();

    contact.setId(resultSet.getLong("id"));
    contact.setFirstName(resultSet.getString("first_name"));
    contact.setLastName(resultSet.getString("last_name"));
    contact.setEmail(resultSet.getString("email"));
    contact.setMessage(resultSet.getString("message"));
    contact.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime().withNano(0));

    return contact;
  }
}
