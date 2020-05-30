package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.Sale;
import com.rk.smartshop.service.SalesService;
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
@RequestMapping(API_VERSION + "/sale")
@RequiredArgsConstructor
@Slf4j
public class SalesController {

  private final SalesService salesService;


  @ApiOperation(value = "Save new sale.", response = Sale.class)
  @PostMapping
  public Sale createSales(@RequestBody Sale sale) {

    log.debug("Create sale {}.", sale);

    return salesService.create(sale);
  }

  @ApiOperation(value = "Update sale.", response = Sale.class)
  @PutMapping("/{saleId}")
  public Sale updateSales(@PathVariable("saleId") Long saleId, @RequestBody Sale sale) {
    log.debug("update sale with id {}.", saleId);

    return salesService.update(saleId, sale);
  }

  @ApiOperation(value = "Delete sale by id.", response = ResponseEntity.class)
  @DeleteMapping("/{saleId}")
  public ResponseEntity<?> deleteSales(@PathVariable(value = "saleId") Long saleId) {
    log.debug("Delete sale by id {}.", saleId);

    salesService.delete(saleId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get sale by sale id", response = Sale.class)
  @GetMapping("/{saleId}")
  public Sale getSales(@PathVariable("saleId") Long saleId) {

    return salesService.getById(saleId);
  }

  @ApiOperation(value = "Get all sales", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<Sale> getAllSales() {

    return salesService.getAllSalesInfo();
  }

}
