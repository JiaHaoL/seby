package com.data.info.dao;

import java.util.List;
import java.util.Map;

public interface OpenDao {
	List<Map<String, Object>> queryForList(Map<String, Object> map) throws Exception;
	
	Object queryForObject(Map<String, Object> map) throws Exception;
	
	Object insert(Map<String, Object> map) throws Exception;

	Integer inserts(Map<String, Object> map) throws Exception;
	
	int delete(Map<String, Object> map) throws Exception;
	
	int update(Map<String, Object> map) throws Exception;
}
