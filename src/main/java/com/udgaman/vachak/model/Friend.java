package com.udgaman.vachak.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="friend")
public class Friend {
        
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	    private String name;
	    private String number;
	    
	    @Transient
	    private String userNumber;
	   
	    
	    @ManyToMany(fetch=FetchType.LAZY, mappedBy="friends")
	    private Set<User> users = new HashSet<User>();
	    
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getNumber() {
	        return number;
	    }

	    public void setNumber(String number) {
	        this.number = number;
	    }

	    
		public Set<User> getUsers() {
			return users;
		}

		public void setUsers(Set<User> users) {
			this.users = users;
		}
		
		

		public String getUserNumber() {
			return userNumber;
		}

		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}

		@Override
	    public boolean equals(Object o) {
	        Friend friend = (Friend)o;
	        return this.number.equals(friend.getNumber());
	    }

	    @Override
	    public int hashCode() {
	        int hash = 3;
	        hash = 53 * hash + (this.number != null ? this.number.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public String toString(){
			return this.name+"  "+this.number;
	    	
	    }

}
