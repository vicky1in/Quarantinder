package com.groun24.quarantinder.Modal;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userprofile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int profileID;

	@Column
	private String bio;

	@Column
	private String photo;

	@Column
	private Timestamp lastOnline;

    @Column
	private int locationID;

	@OneToOne(mappedBy = "profile")
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "ProfileTag", 
             joinColumns = { @JoinColumn(name = "profileID") }, 
             inverseJoinColumns = { @JoinColumn(name = "tagID") })
    private Set<Tag> tags = new HashSet<Tag>();

	@Column(columnDefinition = "varchar(15) default 'No preference'")
	private String genderPreference = "No preference";
	
	@Column(columnDefinition = "integer default 18")
	private int minAgePreference = 18;

	@Column(columnDefinition = "integer default 100")
	private int maxAgePreference = 100;
	
	public UserProfile() {
		// default constructor
	}

	public String getBio() {
		return this.bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhotoPath() {
		if (this.photo == null) return "../../resources/elon_musk.png";
		return "../../../../../../../userphotos/" + this.profileID + "/" + this.photo;
	}
    
    public Timestamp getLastOnline() {
		return this.lastOnline;
	}
	public void setLastOnline(Timestamp timestamp) {
		this.lastOnline = timestamp;
	}

	public Integer getProfileId() {
		return this.profileID;
	}
	public void setProfileId(Integer id) {
		this.profileID = id;
    }
    
    public Integer getLocationId() {
		return this.locationID;
	}
	public void setLocationId(Integer id) {
		this.locationID = id;
	}

	public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGenderPreference() {
		return genderPreference;
	}

	public void setGenderPreference(String genderPreference) {
		this.genderPreference = genderPreference;
	}

	public Integer getMinAgePreference() {
		return minAgePreference;
	}

	public void setMinAgePreference(Integer minAgePreference) {
		this.minAgePreference = minAgePreference;
	}

	public Integer getMaxAgePreference() {
		return maxAgePreference;
	}

	public void setMaxAgePreference(Integer maxAgePreference) {
		this.maxAgePreference = maxAgePreference;
	}
}
