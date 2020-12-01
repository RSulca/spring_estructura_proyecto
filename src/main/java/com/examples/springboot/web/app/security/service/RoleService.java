package com.examples.springboot.web.app.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.springboot.web.app.security.entity.Role;
import com.examples.springboot.web.app.security.enums.NameRole;
import com.examples.springboot.web.app.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Optional<Role> getByNameRole(NameRole nameRole){
		return roleRepository.findByNameRole(nameRole);
	}
	
}
