package com.scenomania.dao;

import com.scenomania.entities.Band;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface BandDao {
	public Band findById(Integer id);
	public Band save(Band band);
	public List<Band> suggest(HashMap params);
	public Band getWhere(HashMap params);
}
