package com.scenomania.dao;

/**
 *
 * @author eugene 
 */
public interface DaoBase <E> {
	public E findById(Integer id);
	public E persistOrMerge(E obj);
}
