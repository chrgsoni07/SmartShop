package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Sale;
import com.rk.smartshop.repository.SaleRepository;
import com.rk.smartshop.service.SalesService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SalesService {

  private static final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);
  private final SaleRepository saleRepository;

  @Override
  public Sale create(Sale sale) {
   log.info("save sales info with data {}", sale);

   Long id = saleRepository.create(sale);
   sale.setId(id);

   return sale;
  }

  @Override
  public Sale update(Long saleId, Sale sale) {
    log.info("update sales info with id {}, data {}", saleId , sale);

    getById(saleId);

    saleRepository.update(sale, saleId);

    return getById(saleId);
  }

  @Override
  public void delete(Long saleId) {
    log.info("delete sale info by id {}", saleId);

    getById(saleId);

    saleRepository.delete(saleId);
  }

  @Override
  public Sale getById(Long saleId) {
    log.info("get sale info by id {}" ,saleId);

    Optional<Sale> optionalSales = saleRepository.getById(saleId);

    return optionalSales.orElseThrow(() -> new ResourceNotFoundException("Sales not found with id " + saleId));
  }

  @Override
  public List<Sale> getAllSalesInfo() {
    log.info("get all sales information");

    return saleRepository.getAll();
  }
}
