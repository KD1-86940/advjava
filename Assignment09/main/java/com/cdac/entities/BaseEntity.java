package com.cdac.entities;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@CreationTimestamp
	@Column(name="created_on")
	private LocalDate createOn;
	@UpdateTimestamp
	@Column(name="updated_on")
	private LocalDate updateOn;
	
}
