package com.scenomania.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(mappedBy="band", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
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
