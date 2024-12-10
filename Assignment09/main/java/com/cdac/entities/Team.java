package com.cdac.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;
@Entity
@Table(name="teams")
@Getter
@Setter
@ToString(callSuper = true,exclude ={"players","adharCard"})
@NoArgsConstructor
public class Team extends BaseEntity{
	@Column(length = 20, name="team_name", unique=true)
	private String name;
	@Column(length = 10, unique=true)
	private String abbravation;
	@Column(length = 10, nullable=false)
	private String owner;
	@Column(name="max_player_age")
	private int age;
	@Column(name="batting_avg")
	private double battingAverage;
	@Column(name="wickets_taken")
	private int wickets;
	@OneToMany (mappedBy="team_id",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Players> players=new ArrayList<>();
	
	
	public Team(String owner,String abbravation)
	{
		this.owner=owner;
		this.abbravation=abbravation;
	}
	public Team(String name, String abbravation, String owner, int age, double battingAverage, int wickets) {
		super();
		this.name = name;
		this.abbravation = abbravation;
		this.owner = owner;
		this.age = age;
		this.battingAverage = battingAverage;
		this.wickets = wickets;
	}
	public void addPlayer(Players player)
	{
		this.players.add(player);
		player.setTeam_id(this);
	}
	public void removePlayer(Players player)
	{
		this.players.remove(player);
		player.setTeam_id(null);
	}
}
