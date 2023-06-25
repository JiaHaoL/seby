package com.data.info.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.data.info.dao.OpenDao;
import com.data.info.vo.OutputVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;


import com.data.info.vo.PageVo;

@RestController
public class BaseController {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	@ModelAttribute
	protected void initAttribute(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.request = request;
		this.response = response;
		this.session = session;
	}

	@Autowired
	OpenDao openDao;
	
	/**
	 * @param code 代码
	 * @param msg 信息
	 * @param obj 数据对象
	 */
	protected OutputVo output(String code, String msg, Object obj) {
		return new OutputVo(code, msg, obj);
	}

	protected OutputVo output(Object obj) {
		return new OutputVo(obj);
	}
	
	protected OutputVo output(Object obj, PageVo pageVo) {
		return new OutputVo(obj, pageVo);
	}

	protected OutputVo outputPage(Map<String, Object> map) {
		try {
			if(map.containsKey("page") && !StringUtils.isEmpty(map.get("page").toString())){
				return new OutputVo(openDao.queryForList(map), (PageVo) map.get("page"));
			}
			return new OutputVo(openDao.queryForList(map));
		}catch (Exception e){
			e.printStackTrace();
		}
		return output(null);
	}
	
	protected OutputVo output(String code,Object obj, PageVo pageVo) {
		return new OutputVo(code,obj, pageVo);
	}

	public static Map<String, Object> getJsonMap(JSONObject json)  {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			PageVo pv = new PageVo();
			if(json.containsKey("page") && !StringUtils.isEmpty(json.getString("page"))){
				JSONObject pageJson = json.getJSONObject("page");
				if(!StringUtils.isEmpty(pageJson.getString("number"))){
					pv.setNumber(Integer.parseInt(pageJson.getString("number")));
				}
				if(!StringUtils.isEmpty(pageJson.getString("current"))){
					Integer current = Integer.parseInt(pageJson.getString("current"));
					if(current > 0){
						pv.setCurrent(current);
					}
				}
				returnMap.put("page", pv);
			}

			if(json.containsKey("data") && !StringUtils.isEmpty(json.getString("data"))){
				JSONObject dataJson = json.getJSONObject("data");
				for(String str : dataJson.keySet()){
					returnMap.put(str, dataJson.get(str));
				}
			}

			for(String str : json.keySet()){
				if(!str.equals("page")){
					returnMap.put(str, json.get(str));
				}
			}

			returnMap.put("UUID", UUID.randomUUID().toString().replace("-", ""));
			returnMap.put("updateTime", df.format(new Date()));
			returnMap.put("appName", "coloroa");
		}catch (Exception e){
			e.printStackTrace();
		}

		return returnMap;
	}
	
	
	/**
	 * 从request中获得参数Map，返回Map
	 * @return Map<String, Object
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String, Object> getParameterMap() {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}

			returnMap.put(name, value);
		}

		return returnMap;
	}

	public String getToken(){
		String token = request.getParameter("access_token");
		if(token == null){
			token = request.getHeader("access_token");;
		}
		return token;
	}
	
}
