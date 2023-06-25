package com.data.info.vo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseVo implements Serializable {
	
	private static final long serialVersionUID = -2344780605735233287L;

	public List<String> getField(Object model) {
		List<String> list = new ArrayList<String>();

		java.lang.reflect.Method[] method = model.getClass().getDeclaredMethods();// 获取对象所有方法
		for (java.lang.reflect.Method m : method) {
			
			if (m.getName().startsWith("get")) {// 获取get方法
				Object o = null;
				try {
					o = m.invoke(model);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 执行
				if (o == null ) {
					list.add("");
				} else {
					list.add(o.toString());
				}
			}
		}
		return list;
	}


}
