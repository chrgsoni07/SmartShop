package com.rk.smartshop.service.impl;

import com.rk.smartshop.model.ProductLocation;
import com.rk.smartshop.repository.ProductLocationRepo;
import com.rk.smartshop.service.ProductLocationService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductLocationServiceImpl implements ProductLocationService {

  private final ProductLocationRepo productLocationRepo;

  @Override
  public List<Long> getAllProductIdsByLocationId(Long locationId) {

    List<ProductLocation> productLocationList = productLocationRepo.getAllByLocationId(locationId);

    return productLocationList.stream().map(p-> p.getProductId()).collect(Collectors.toList());
  }
}
