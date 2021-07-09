package com.in.fmc.userservice.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "REGISTER")
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_no")
	private Long mobileNo;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy = "register")
	private Login login;

}
