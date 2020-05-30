package com.rk.smartshop.mapper;

import com.rk.smartshop.model.Location;
import com.rk.smartshop.model.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper implements RowMapper<Location> {

  @Override
  public Location mapRow(ResultSet resultSet, int i) throws SQLException {

    Location location = new Location();
    location.setId(resultSet.getLong("id"));
    location.setName(resultSet.getString("name"));

    return location;
  }
}
