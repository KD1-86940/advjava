package com.cdac.entities;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="players")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Players extends BaseEntity{
	@Column(length = 20, name="first_name")
    private String first_name;
	@Column(length = 20, name="last_name")
    private String last_name;
    private LocalDate dob;
    @Column(name="batting_avg")
	private double battingAverage;
	@Column(name="wickets_taken")
	private int wickets;
	
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team_id;
	
	
	
	public Players(String first_name, String last_name, LocalDate dob, double battingAverage, int wickets) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.battingAverage = battingAverage;
		this.wickets = wickets;
	}


	
	
	
	
}
