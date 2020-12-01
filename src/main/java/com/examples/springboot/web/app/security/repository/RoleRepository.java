package com.examples.springboot.web.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examples.springboot.web.app.security.entity.Role;
import com.examples.springboot.web.app.security.enums.NameRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByNameRole(NameRole nameRole);
	
}
