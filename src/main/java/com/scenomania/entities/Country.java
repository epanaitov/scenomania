package com.scenomania.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="countries", uniqueConstraints = { @UniqueConstraint(columnNames={"code"}) })
public class Country extends EntityBase implements Localized {

	@NotNull
	private String name;

	@NotNull
	@Length(min=2,max=2)
	@Column(columnDefinition = "char")
	private String code;
	
	@Column
	private String slug;

	@OneToMany(mappedBy="country", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private Set<CountryLocale> locales;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private Set<Area> areas;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CountryLocale> getLocales() {
		return locales;
	}

	public void setLocales(Set<CountryLocale> locales) {
		this.locales = locales;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
}
