package gr.pr.date_releases.dao;

import java.util.List;

public interface GenericDao {
	
	void save(Object entity);
	void update(Object entity);
	void saveOrUpdate(Object entity);
	void delete(Object entity);
	void insertMultipleLines(List<Object> entities);
	
}
