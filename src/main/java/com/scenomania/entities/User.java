package com.scenomania.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="users", uniqueConstraints = { @UniqueConstraint(columnNames={"email"}) })
public class User extends EntityBase {
	
	/*
	@ManyToMany(
		targetEntity=Band.class,
		fetch=FetchType.EAGER,
		cascade = CascadeType.ALL
	)
	@JoinTable(
		name="bands_users",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="band_id")
	)
	@AttributeOverrides({
		//@AttributeOverride(name="bandId", column = @Column(name="band_id") ),
        //@AttributeOverride(name="userId", column = @Column(name="user_id") ),
		@AttributeOverride(name="position", column = @Column(name="position") )
	})
	
	@MapKeyClass(BandPosition.class)
	private Map<BandPosition, Band> bands = new HashMap<BandPosition, Band>();
	*/

	@OneToMany(mappedBy="user", cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch=FetchType.EAGER)
	private Set<BandPosition> playingIn;

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<PromoterPosition> promotingIn;

	@ManyToOne
	@JoinColumn(name="homecity_id")
	private City homecity;

	@Column
	private Date created_at;

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}


	@NotNull
	@Column
	@Size.List( { @Size(min=2, message="user.nickname.short"), @Size(max=250, message="user.nickname.long") } )
    private String nickname;

	@NotNull
	private String password;

	private String salt;

	@NotNull
	@Pattern(regexp="(?i)[A-Z0-9._%+-]+@(?i)[A-Z0-9.-]+\\.(?i)[A-Z]{2,4}", message="user.email.invalid")
	@Column(name="email", nullable=false, length=200, unique=true)
	private String email;

	
	private Date last_login;

	
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	//@Temporal(TemporalType.DATE)
	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	@Column
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public City getHomecity() {
		return homecity;
	}

	public void setHomecity(City homecity) {
		this.homecity = homecity;
	}

	public Set<BandPosition> getPlayingIn() {
		return playingIn;
	}

	public void setPlayingIn(Set<BandPosition> playingIn) {
		this.playingIn = playingIn;
	}

	public Set<PromoterPosition> getPromotingIn() {
		return promotingIn;
	}

	public void setPromotingIn(Set<PromoterPosition> promotingIn) {
		this.promotingIn = promotingIn;
	}
}
