package com.rk.smartshop.service;

import com.rk.smartshop.model.Sale;
import java.util.List;

public interface SalesService {

  Sale create(Sale sale);

  Sale update(Long saleId, Sale sale);

  void delete(Long saleId);

  Sale getById(Long saleId);

  List<Sale> getAllSalesInfo();
}
