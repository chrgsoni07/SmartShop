package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Product;
import com.rk.smartshop.repository.ProductRepository;
import com.rk.smartshop.service.ProductLocationService;
import com.rk.smartshop.service.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
  private final ProductRepository productRepository;
  private final ProductLocationService productLocationService;

  @Override
  public List<Product> getAllProducts() {
    log.info("get all products");

    return productRepository.getAll();
  }

  @Override
  public Product getById(Long productsId) {
    log.info("Get product info by id {}", productsId);
    Optional<Product> productOptional = productRepository.getById(productsId);

    return productOptional.orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productsId));
  }

  @Override
  public void delete(Long productId) {
    log.info("delete product by id {}", productId);

    getById(productId);
    productRepository.delete(productId);
  }

  @Override
  public Product update(Long productId, Product product) {
    log.info("update product info by id {}, data {}", productId, product);

    productRepository.update(product, productId);

    return getById(productId);
  }

  @Override
  public Product create(Product product) {
    log.info("create product with data ", product);

    Long id = productRepository.create(product);
    product.setId(id);

    return product;
  }

  @Override
  public List<Product> getAllProductsByLocationId(Long locationId) {
    log.info("get all products by location id {}", locationId);

    List<Long> productIds = productLocationService.getAllProductIdsByLocationId(locationId);

    return productIds.stream().map(id -> getById(id)).collect(Collectors.toList());
  }
}
