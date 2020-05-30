package com.rk.smartshop.mapper;

import com.rk.smartshop.model.UserRoleType;
import com.rk.smartshop.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {

    User user = new User();
    user.setId(resultSet.getLong("id"));
    user.setFirstName(resultSet.getString("first_name"));
    user.setLastName(resultSet.getString("last_name"));
    user.setEmail(resultSet.getString("email"));
    user.setPassword(resultSet.getString("password"));
    user.setAddress(resultSet.getString("address"));
    user.setPhone(resultSet.getString("phone"));
    user.setType(UserRoleType.valueOf(resultSet.getString("type")));
    user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime().withNano(0));

    return user;
  }
}
