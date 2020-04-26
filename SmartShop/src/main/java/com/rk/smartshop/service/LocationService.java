package com.rk.smartshop.service;

import com.rk.smartshop.model.Location;
import java.util.List;

public interface LocationService {
  List<Location> getAllLocation();

  Location getById(Long locationId);

  void delete(Long locationId);

  Location update(Long locationId, Location location);

  Location create(Location location);
}
