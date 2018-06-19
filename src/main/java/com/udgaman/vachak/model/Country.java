package com.udgaman.vachak.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="country")
public class Country {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonView(View.matched.class)
	private String numberCode;
	@JsonView(View.matched.class)
	private String twoLetterCode;
	@JsonView(View.matched.class)
	private String threeLetterCode;
	@JsonView(View.matched.class)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="country",cascade=CascadeType.ALL)
	private Set<User> users = new HashSet<User>();
	
	public Country(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getNumberCode() {
		return numberCode;
	}
	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}
	public String getTwoLetterCode() {
		return twoLetterCode;
	}
	public void setTwoLetterCode(String twoLetterCode) {
		this.twoLetterCode = twoLetterCode;
	}
	public String getThreeLetterCode() {
		return threeLetterCode;
	}
	public void setThreeLetterCode(String threeLetterCode) {
		this.threeLetterCode = threeLetterCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Set<User> getUsers() {
		return users;
	}



	public void setUsers(Set<User> users) {
		this.users = users;
	}



	@Override
	public String toString(){
		return this.name;
	}
}
