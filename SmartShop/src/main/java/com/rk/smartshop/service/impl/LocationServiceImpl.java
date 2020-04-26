package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Location;
import com.rk.smartshop.repository.LocationRepository;
import com.rk.smartshop.service.LocationService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

  private static final Logger log = LoggerFactory.getLogger(OrderDetailServiceImpl.class);
  private final LocationRepository locationRepository;

  @Override
  public List<Location> getAllLocation() {
    log.info("get all location");

    return locationRepository.getAll();
  }

  @Override
  public Location getById(Long locationId) {
    log.info("get location by id {}" , locationId);

    Optional<Location> optionalLocation = locationRepository.getById(locationId);

    return optionalLocation.orElseThrow(() -> new ResourceNotFoundException("location not found with id "+ locationId));
  }

  @Override
  public void delete(Long locationId) {
    log.info("delete location by id {} ", locationId);

      getById(locationId);

      locationRepository.delete(locationId);
  }

  @Override
  public Location update(Long locationId, Location location) {
    log.info("update location with id {} ,data {}", locationId, location);

    locationRepository.update(location, locationId);

    return getById(locationId);
  }

  @Override
  public Location create(Location location) {
    log.info("create location with data {}", location);

    Long id = locationRepository.create(location);
    location.setId(id);

    return location;
  }
}
