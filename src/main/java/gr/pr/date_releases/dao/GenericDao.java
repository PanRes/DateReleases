package gr.pr.date_releases.dao;

import java.util.List;

public interface GenericDao {
	
	void upsert(Object entity);
	void saveOrUpdate(Object entity);
	void deleteLine(Object entity);
	void insertMultipleLines(List<Object> entities);
	
}
