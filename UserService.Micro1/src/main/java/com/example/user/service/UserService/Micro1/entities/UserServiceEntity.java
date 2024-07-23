package com.example.user.service.UserService.Micro1.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="micro_user")
public class UserServiceEntity {
	@Id
	private String userId;
	private String name;
	private String email;
	private String about;
	
	@Transient // means it is not save in database
	private List< Rating> ratings= new ArrayList<>();

	
}
