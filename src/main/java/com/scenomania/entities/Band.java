package com.scenomania.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author eugene
 */

@Entity
@Table(
	name="bands",
	uniqueConstraints = {@UniqueConstraint(columnNames={"name", "homecity_id"})}
)
public class Band extends EntityBase {

	/*
	@ManyToMany(
		targetEntity=User.class,
		fetch=FetchType.EAGER,
		cascade = CascadeType.ALL
	)
	@JoinTable(
		name="bands_users",
		joinColumns=@JoinColumn(name="band_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	@AttributeOverrides({
		//@AttributeOverride(name="bandId", column = @Column(name="band_id") ),
        //@AttributeOverride(name="userId", column = @Column(name="user_id") ),
		//@AttributeOverride(name="position", column = @Column(name="position") )
	})
	@MapKeyClass(BandPosition.class)
	private Map<BandPosition, User> members = new HashMap<BandPosition, User>();
	*/

	@OneToMany(mappedBy="band")
	private Set<BandPosition> members;

	@ManyToOne
	@JoinColumn(name="homecity_id")
	private City homecity;

	@Column
	private String name;

	@Column(columnDefinition = "mediumtext")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getHomecity() {
		return homecity;
	}

	public void setHomecity(City homecity) {
		this.homecity = homecity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BandPosition> getMembers() {
		return members;
	}

	public void setMembers(Set<BandPosition> members) {
		this.members = members;
	}

}
