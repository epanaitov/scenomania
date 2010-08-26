package com.scenomania.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="promoters")
public class Promoter extends EntityBase {

	@ManyToOne
	@JoinColumn(name="homecity_id", insertable=false, updatable=false)
	private City homecity;

	@Column
	private String name;

	@OneToMany(mappedBy="promoter")
	private Set<PromoterPosition> staff;

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

	public Set<PromoterPosition> getStaff() {
		return staff;
	}

	public void setStaff(Set<PromoterPosition> staff) {
		this.staff = staff;
	}

}
