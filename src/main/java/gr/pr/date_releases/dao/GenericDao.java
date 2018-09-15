package gr.pr.date_releases.dao;

import java.util.List;

public interface GenericDao {
	
	void upsert(Object entity);
	void save(Object entity);
	void saveOrUpdate(Object entity);
	void deleteRow(Object entity);
	void insertMultipleLines(List<Object> entities);
	
}
