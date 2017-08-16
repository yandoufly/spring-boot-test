package com.yjy.core.vo.query;

import java.util.Map;

public interface NativeQueryVo<T> extends QueryVo<T> {
	
	/**获取native SQL语句*/
	String getQuerySQL();
	
	/**请求参数集合*/
	Map<String, Object> getParameterMap();
	
	/**获取多个一个参数名的多个参数，例如check box 多个值*/
	Object[] getParameterValues(String name);
	
	/**获取请求参数值*/
	Object getParameter(String name);
	
	/**获取SQL file name or SQL name*/
	String getSQLId();
}
