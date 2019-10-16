package com.hampcode.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.model.entity.Product;
import com.hampcode.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private Environment env;

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getAll()
				      .stream()
				      .map(product->{
				    	  product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
				    	  return product;
				      }).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getOne(id);
		
		product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}



