package com.yjy.core.vo.query;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.cglib.core.Predicate;

/** 通过 Specification 接口方式查询接口*/
public interface JpaQueryVo<T> extends QueryVo<T> {
	
	Predicate getPredicates();
	
	CriteriaBuilder getCriteriaBuilder();
}
