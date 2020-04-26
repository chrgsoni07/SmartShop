package com.rk.smartshop.exception;

import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "%s not found with %s : '%s'";

  public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format(MESSAGE_TEMPLATE, resourceName, fieldName, fieldValue));
  }

  public ResourceNotFoundException(String message) {

    super(message);
  }
}