package com.hampcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.model.entity.Item;
import com.hampcode.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public ResponseEntity<List<Item>> getItems() {
		List<Item> items = itemService.getAll();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@GetMapping("/product/{productId}/quantity/{quantity}")
	public ResponseEntity<Item> addProductCart(@PathVariable Long productId,@PathVariable Integer quantity) {
		Item item = itemService.shoppingCart(productId, quantity);
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
}
