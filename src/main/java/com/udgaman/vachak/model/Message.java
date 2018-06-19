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

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="message")
public class Message {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@JsonView(View.message.class)
		private int id;
		
		@JsonView(View.message.class)
	    private String toPhoneNumber;
		
		@JsonView(View.message.class)
	    private String fromPhoneNumber;
		
		@JsonView(View.message.class)
	    private String message;
		
		@JsonView(View.message.class)
	    private String type;
		
		@JsonView(View.message.class)
	    private String sent;
		
		@JsonView(View.message.class)
	    private String delivery;
		
		@JsonView(View.message.class)
	    private String seen;
	    
	    @ManyToMany(fetch=FetchType.LAZY, mappedBy="messages")
	    private Set<User> users = new HashSet<User>();

	    public String getToPhoneNumber() {
	        return toPhoneNumber;
	    }

	    public void setToPhoneNumber(String toPhoneNumber) {
	        this.toPhoneNumber = toPhoneNumber;
	    }

	    public String getSent() {
	        return sent;
	    }

	    public void setSent(String sent) {
	        this.sent = sent;
	    }

	    public String getSeen() {
	        return seen;
	    }

	    public void setSeen(String seen) {
	        this.seen = seen;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
	    
	    public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getFromPhoneNumber() {
	        return fromPhoneNumber;
	    }

	    public void setFromPhoneNumber(String fromPhoneNumber) {
	        this.fromPhoneNumber = fromPhoneNumber;
	    }

	    public String getDelivery() {
	        return delivery;
	    }

	    public void setDelivery(String delivery) {
	        this.delivery = delivery;
	    }

		public Set<User> getUsers() {
			return users;
		}

		public void setUsers(Set<User> users) {
			this.users = users;
		}

	
	    
	    


}
