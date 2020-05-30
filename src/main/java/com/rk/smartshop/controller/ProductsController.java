package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.Product;
import com.rk.smartshop.service.ProductService;
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
@RequestMapping(API_VERSION + "/product")
@RequiredArgsConstructor
@Slf4j
public class ProductsController {

  private final ProductService productService;


  @ApiOperation(value = "Save new product.", response = Product.class)
  @PostMapping
  public Product createProducts(@RequestBody Product product) {

    log.debug("Create products {}.", product);

    return productService.create(product);
  }

  @ApiOperation(value = "Update product.", response = Product.class)
  @PutMapping("/{productId}")
  public Product updateProducts(@PathVariable("productId") Long productId, @RequestBody Product product) {
    log.debug("update product with id {}.", productId);

    return productService.update(productId, product);
  }

  @ApiOperation(value = "Delete product by id.", response = ResponseEntity.class)
  @DeleteMapping("/{productId}")
  public ResponseEntity<?> deleteProducts(@PathVariable(value = "productId") Long productId) {
    log.debug("Delete product by id {}.", productId);

    productService.delete(productId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get product by products id", response = Product.class)
  @GetMapping("/{productId}")
  public Product getProduct(@PathVariable("productId") Long productsId) {

    return productService.getById(productsId);
  }

  @ApiOperation(value = "Get all products", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<Product> getAllProducts() {

    return productService.getAllProducts();
  }

  @ApiOperation(value = "Get all products by location ic", response = List.class)
  @GetMapping("/all/{locationId}/location")
  public List<Product> getAllProductsByLocation(@PathVariable("locationId") Long locationId) {

    return productService.getAllProductsByLocationId(locationId);
  }
}
