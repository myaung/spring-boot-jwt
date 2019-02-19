package com.springboot.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.jwt.entity.ROLE_NAME;
import com.springboot.jwt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleName(ROLE_NAME roleName);
}
