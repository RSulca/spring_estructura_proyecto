package com.examples.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examples.springboot.web.app.entity.Product;
import com.examples.springboot.web.app.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> listProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Product> getProduct(int id) {
		return productRepository.findById(id);
	}
	
	public Optional<Product> getByName(String name){
		return productRepository.findByName(name);
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(int id) {
		productRepository.deleteById(id);
	}
	
	public boolean existById(int id) {
		return productRepository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		return productRepository.existsByName(name);
	}
	
}
