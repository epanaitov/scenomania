package com.scenomania.entities;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.persistence.PrePersist;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBase {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

    public Integer getId() {
        return id;
    }

	public void setFromHash(Map dataHash) {
		Iterator itr = dataHash.keySet().iterator();

		Class clazz = this.getClass();

		while (itr.hasNext()) {
			String key = itr.next().toString();

			try {

				Field field = clazz.getDeclaredField(key);
				field.setAccessible(true);
				field.set(this, dataHash.get(key));

			} catch (Exception e) {
				//System.out.println(e);
			}

			

		}
		
	}

}
