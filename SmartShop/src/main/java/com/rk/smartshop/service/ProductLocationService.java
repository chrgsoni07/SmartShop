package com.rk.smartshop.service;

import java.util.List;

public interface ProductLocationService {

  List<Long> getAllProductIdsByLocationId(Long locationId);
}
