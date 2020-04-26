package com.rk.smartshop.service;

import com.rk.smartshop.model.Product;
import java.util.List;

public interface ProductService {

  List<Product> getAllProducts();

  Product getById(Long productsId);

  void delete(Long productId);

  Product update(Long productId, Product product);

  Product create(Product product);

  List<Product> getAllProductsByLocationId(Long locationId);
}
