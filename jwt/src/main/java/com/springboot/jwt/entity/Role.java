package com.springboot.jwt.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NaturalId
	@Enumerated(EnumType.STRING)
	@Size(max = 50)
	private ROLE_NAME roleName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ROLE_NAME getRoleName() {
		return roleName;
	}

	public void setRoleName(ROLE_NAME roleName) {
		this.roleName = roleName;
	}
	
}
