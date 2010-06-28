package com.scenomania.entities;

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
public class Country extends EntityBase {

	@NotNull
	private String name;

	@NotNull
	@Length(min=2,max=2)
	@Column(columnDefinition = "char")
	private String code;

	@OneToMany(mappedBy="country", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private Set<CountryLocale> locales;

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
	
}
