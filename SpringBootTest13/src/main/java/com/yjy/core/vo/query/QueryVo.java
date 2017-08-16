package com.yjy.core.vo.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.yjy.core.vo.Vo;

/** QueryVo 顶级接口*/
public interface QueryVo<T> extends Vo {
	
	/** 分页接口*/
	Pageable getPageable();
	
	/** 排序对象*/
	Sort getSort();
}
