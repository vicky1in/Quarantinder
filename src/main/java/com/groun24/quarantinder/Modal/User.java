package com.groun24.quarantinder.Modal;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
	@Column
	private String Name;
	@Column
	public String username;
	
    @Column
	private String email;
	@Column
	private String Password;
	
    @Transient
    private String passwordConfirm;
    
    @Transient
    private String oldPasswordConfirm;

    @ManyToMany
    private Set<Role> roles;

	@Column
	private Date DOB;
	
	@Column
	private String Gender;

	// For some reason phone number being added breaks it ??
	@Column
	private String phonenumber;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int UserID;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileID", referencedColumnName = "profileID")
	private UserProfile profile;

	public String getName() {
		return this.Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String name) {
		this.username = name;
	}	
	
	public Integer getId() {
		return UserID;
	}	

	public void setId(Integer id) {
		this.UserID = id;
	}

	public UserProfile getProfile() {
		return this.profile;
	}
	
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.Password;
	}
	public void setPassword(String pw) {
		this.Password = pw;
	}
	
	public String getPhonenumber() {
		return this.phonenumber;
	}
	public void setPhonenumber(String ph) {
		this.phonenumber = ph;
	}
	
	public Date getDob() {
		return DOB;
	}
	public void setDob(Date dob) {
		this.DOB = dob;
	}
	
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		this.Gender = gender;
	}
	
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public String getOldPasswordConfirm() {
        return oldPasswordConfirm;
    }
    public void setOldPasswordConfirm(String passwordConfirm) {
        this.oldPasswordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
	

}
