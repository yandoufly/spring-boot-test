package com.yjy.core.repository;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yjy.core.entity.AbsIdEntity;

/**步骤三：实现BaseRepository
 * 继承SimpleJpaRepository类，使其拥有Jpa Repository的基本方法
 */
@Transactional
public class BaseRepositoryImpl<T extends AbsIdEntity> extends SimpleJpaRepository<T, Serializable> implements BaseRepository<T> {

	@SuppressWarnings("unused")
    private final EntityManager entityManager;
    
	/**
	 * 实现第二个构造函数，拿到domainClass和EntityManager两个对象
	 * 知道某个Repository是否支持某个领域对象的类型，因此在实现构造函数时我们将domainClass的信息保留下来
	 */
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(JpaEntityInformation<T, Serializable> information, EntityManager entityManager){
    	super(information, entityManager);
    	this.entityManager = entityManager;
    }
    
    @Override
    public <S extends T> S save(S entity) {
        entity.setModificationTimestamp(new Timestamp(System.currentTimeMillis())); //修改时间
        return super.save(entity);
    }
    
    /**只做逻辑删除*/
	@Override
	public void delete(T entity) {
		entity.setDr(1);
		save(entity);
	}

	/**只做逻辑删除*/
	@Override
	public void delete(Serializable id) {
		T entity = findOne(id);
		entity.setDr(1);
		this.save(entity);
	}
	
}
