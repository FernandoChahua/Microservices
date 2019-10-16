package com.hampcode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Product;
import com.hampcode.model.repository.ProductRepository;
import com.hampcode.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().iterator().forEachRemaining(products::add);
		return products;
	}

	@Override
	public Product getOne(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product Not Found!"));
	}

}
