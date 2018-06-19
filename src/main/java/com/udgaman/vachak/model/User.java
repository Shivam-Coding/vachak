package com.udgaman.vachak.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.matched.class)
	private int id;
	@JsonView(View.matched.class)
	private String firstName;
	@JsonView(View.matched.class)
	private String lastName;
	@JsonView(View.matched.class)
	private String phone;


	
	
	
	private String deviceID;
	
	@JsonView(View.matched.class)
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "country_ID")
	private Country country;
	
	private String gcm;
	private String profile;
	
	@JsonView(View.matched.class)
	private String status;
	@JsonView(View.matched.class)
	private String thumbnail;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_message", joinColumns = {
			@JoinColumn(name = "user_ID") },
			inverseJoinColumns = { @JoinColumn(name = "message_ID") })
	private List<Message> messages = new LinkedList<Message>();
	
	
	private int verification;
	

	public User(){
		
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_friend", joinColumns = {
			@JoinColumn(name = "user_ID") },
			inverseJoinColumns = { @JoinColumn(name = "friend_ID") })
	private Set<Friend> friends = new HashSet<Friend>();
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDeviceID() {
		return deviceID;
	}


	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public String getGcm() {
		return gcm;
	}


	public void setGcm(String gcm) {
		this.gcm = gcm;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public int getVerification() {
		return verification;
	}


	public void setVerification(int verification) {
		this.verification = verification;
	}

	public Set<Friend> getFriends() {
		return friends;
	}


	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	


	public List<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	@Override
	public String toString(){
		return this.firstName+" "+this.lastName;
	}
	
	

}
