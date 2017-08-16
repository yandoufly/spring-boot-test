//package com.yjy.core.vo.query;
//
//import javax.persistence.criteria.CriteriaBuilder;
//
//import org.springframework.cglib.core.Predicate;
//
//public class AbstractJpaQueryVo<T> extends AbstractQueryVo<T> implements JpaQueryVo<T> {
//
//	private static final long serialVersionUID = 1L;
//	
//	private CriteriaBuilder builder;
//	
//	@SuppressWarnings("unchecked")
//	public AbstractJpaQueryVo() {
//		// 取泛型类型T
//		Class<T> clazz = (Class<T>) ReflectionUtils.getGenericClass(getClass(), 0);
//		builder = PredicateUtils.createWJCriteriaBuilder(clazz);
//	}
//	
//	@Override
//	public Predicate getPredicates() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CriteriaBuilder getCriteriaBuilder() {
//		return builder;
//	}
//
//}
