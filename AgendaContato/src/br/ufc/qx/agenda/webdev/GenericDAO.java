package br.ufc.qx.agenda.webdev;

import java.sql.Connection;
import java.util.List;

public abstract class GenericDAO<T> {
	
	protected Connection con;
	
	public GenericDAO() {
		con = new ConnectionFactory().getConnection();
	}
	
	public abstract List<T> getAll();
	public abstract boolean insert(T entity);
	public abstract boolean remove(T entity);
	public abstract T getById(int id);
	public abstract boolean update(T entity);
	
}
