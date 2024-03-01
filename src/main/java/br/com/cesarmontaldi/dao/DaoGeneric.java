package br.com.cesarmontaldi.dao;

import br.com.cesarmontaldi.jpautil.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DaoGeneric<Entity> {
	
	public void salvar(Entity entity) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(entity);
		
		transaction.commit();
		entityManager.close();

	}
	
	public Entity salvarUser(Entity entity) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Entity user = entityManager.merge(entity);
		
		transaction.commit();
		entityManager.close();
		
		return user;

	}
	
	public void deleteUser(Entity entity) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.remove(entity);
		
		transaction.commit();
		entityManager.close();

	}
	
	public void deleteUserId(Entity entity) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Object id = JpaUtil.getPrimaryKey(entity);
		entityManager.createQuery("delete from " + entity.getClass().getSimpleName() + " where id = " + id).executeUpdate();
		
		transaction.commit();
		entityManager.close();

	}

}