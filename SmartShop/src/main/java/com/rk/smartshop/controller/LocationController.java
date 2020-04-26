package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.Location;
import com.rk.smartshop.service.LocationService;
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
@RequestMapping(API_VERSION + "/location")
@RequiredArgsConstructor
@Slf4j
public class LocationController {

  private final LocationService locationService;

  @ApiOperation(value = "Save new location.", response = Location.class)
  @PostMapping
  public Location createLocation(@RequestBody Location location) {

    log.debug("Create location {}.", location);

    return locationService.create(location);
  }

  @ApiOperation(value = "Update location.", response = Location.class)
  @PutMapping("/{locationId}")
  public Location updateLocation(@PathVariable("locationId") Long locationId, @RequestBody Location location) {
    log.debug("Create location with id {}.", locationId);

    return locationService.update(locationId, location);
  }

  @ApiOperation(value = "Delete location by id.", response = ResponseEntity.class)
  @DeleteMapping("/{locationId}")
  public ResponseEntity<?> deleteLocation(@PathVariable(value = "locationId") Long locationId) {
    log.debug("Delete location by id {}.", locationId);

    locationService.delete(locationId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get location by location id", response = Location.class)
  @GetMapping("/{locationId}")
  public Location getLocation(@PathVariable("locationId") Long locationId) {

    return locationService.getById(locationId);
  }

  @ApiOperation(value = "Get all categories categories for of product.", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<Location> getCategories() {

    return locationService.getAllLocation();
  }
}
