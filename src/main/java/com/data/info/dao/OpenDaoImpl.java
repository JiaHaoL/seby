package com.data.info.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.data.info.vo.PageVo;

@Mapper
@Repository("openDao")
public class OpenDaoImpl extends SqlSessionDaoSupport implements OpenDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<Map<String, Object>> queryForList(Map<String, Object> map) throws Exception {
		Object obj = map.get("page");
		if (null != obj) {
			PageVo pageVo = (PageVo) obj;
			Page<?> page = PageHelper.startPage(pageVo.getCurrent(), pageVo.getNumber(), true);
			List<Map<String, Object>> list = sqlSession.selectList(map.get("sqlMapId").toString(), map);
			pageVo.setTotal((int) page.getTotal());
			return list;
		} else {
			List<Map<String, Object>> list = sqlSession.selectList(map.get("sqlMapId").toString(), map);
			return list;
		}
	}

	public Object queryForObject(Map<String, Object> map) throws Exception {
		Object result = sqlSession.selectOne(map.get("sqlMapId").toString(), map);
		return result;
	}

	public Object insert(Map<String, Object> map) {
		String uuid = UUID.randomUUID().toString();
		map.put("UUID", uuid);
		if (sqlSession.insert(map.get("sqlMapId").toString(), map) > 0) {
			return uuid;
		} else {
			return null;
		}

	}

	public Integer inserts(Map<String, Object> map)  {
		String uuid = UUID.randomUUID().toString();
		map.put("UUID", uuid);
		return sqlSession.insert(map.get("sqlMapId").toString(), map);

	}

	public int delete(Map<String, Object> map) throws Exception {
		return sqlSession.delete(map.get("sqlMapId").toString(), map);
	}

	public int update(Map<String, Object> map) throws Exception {
		return sqlSession.update(map.get("sqlMapId").toString(), map);
	}

}
