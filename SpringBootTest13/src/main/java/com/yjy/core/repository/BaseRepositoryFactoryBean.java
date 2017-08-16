package com.yjy.core.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.yjy.core.entity.AbsIdEntity;

/**步骤四：创建一个自定义的BaseRepositoryFactoryBean来代替默认的RepositoryFactoryBean
 * RepositoryFactoryBean负责返回一个RepositoryFactory，Spring Data Jpa 将使用RepositoryFactory来创建Repository具体实现
 * 这里我们用BaseDaoImpl代替SimpleJpaRepository作为Repository接口的实现
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, Serializable>, T extends AbsIdEntity>
		extends JpaRepositoryFactoryBean<R, T, Serializable> {

	public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}
	
	@Override
    protected RepositoryFactorySupport createRepositoryFactory(final EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager) {

            protected SimpleJpaRepository<T, Serializable> getTargetRepository(
                    RepositoryInformation information,    EntityManager entityManager) {
                Class<T> domainClass = (Class<T>) information.getDomainType();
                // 对于继承了AbsIdEntity的实体类，返回自定义的BaseRepository（也就是BaseDaoImpl），否则就返回SimpleJpaRepository
                if(AbsIdEntity.class.isAssignableFrom(domainClass)) {
                    return new BaseRepositoryImpl(domainClass, entityManager);
                } else { 
                    return new SimpleJpaRepository(domainClass, entityManager);
                }
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return metadata.getDomainType().isAssignableFrom(AbsIdEntity.class) ? BaseRepositoryImpl.class : SimpleJpaRepository.class;
            }
        };
    }
}
