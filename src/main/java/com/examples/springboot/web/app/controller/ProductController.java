package com.examples.springboot.web.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.springboot.web.app.dto.ProductDto;
import com.examples.springboot.web.app.entity.Product;
import com.examples.springboot.web.app.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/product")
	public ResponseEntity<Map<String, Object>> listProducts() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Product> list = productService.listProducts();
			map.put("ok", true);
			map.put("data", list);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("ok", false);
			map.put("message", e);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product/id/{id}")
	public ResponseEntity<Map<String, Object>> getProductById(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		try {
			if (productService.existById(id)) {
				Product product = productService.getProduct(id).get();
				map.put("ok", true);
				map.put("data", product);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				map.put("ok", false);
				map.put("message", "No existe el producto.");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("data", e);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/name/{name}")
	public ResponseEntity<Map<String, Object>> getProductByName(@PathVariable String name) {
		Map<String, Object> map = new HashMap<>();
		try {
			if (productService.existsByName(name)) {
				Product product = productService.getByName(name).get();
				map.put("ok", true);
				map.put("data", product);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				map.put("ok", false);
				map.put("message", "No existe el producto.");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("data", e);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/product")
	public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ProductDto product) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(StringUtils.isBlank(product.getName())) {
				map.put("ok", false);
				map.put("message", "Nombre es obligatorio.");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			if(product.getPrice()<0) {
				map.put("ok", false);
				map.put("message", "Precio debe ser mayor a cero.");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			if(productService.existsByName(product.getName())) {
				map.put("ok", false);
				map.put("message", "El nombre ya existe.");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			Product pro = new Product(product.getName(), product.getPrice());
			productService.save(pro);
			map.put("ok", true);
			map.put("data", pro);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("ok", false);
			map.put("message", e);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody ProductDto product, @PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(!productService.existById(id)) {
				map.put("ok", false);
				map.put("message", "Producto no existe");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			
			Product pro = productService.getProduct(id).get();  
			pro.setName(product.getName());
			pro.setPrice(product.getPrice());
			productService.save(pro);
			map.put("ok", true);
			map.put("data", pro);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("ok", false);
			map.put("message", e);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(!productService.existById(id)) {
				map.put("ok", false);
				map.put("message", "Producto no existe");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			productService.delete(id);
			map.put("ok", true);
			map.put("message", "Producto borrado.");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("ok", false);
			map.put("message", e);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
