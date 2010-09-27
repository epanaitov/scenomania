package com.scenomania.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="tags")
public class Tag extends EntityBase {

	@Column
	private String name;	

}
