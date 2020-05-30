package com.rk.smartshop.repository;

import com.rk.smartshop.model.ProductLocation;
import com.rk.smartshop.repository.main.CommonRepository;
import java.util.List;

public interface ProductLocationRepo extends CommonRepository<ProductLocation> {

  List<ProductLocation> getAllByLocationId(Long locationId);
}
