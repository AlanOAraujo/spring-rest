package br.com.devmedia.curso.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.lang.reflect.ParameterizedType;

import org.springframework.stereotype.Repository;

@Repository
public class AbstractDAO<E extends Serializable> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<E> daoType;

	public AbstractDAO() {}

	@SuppressWarnings("unchecked")
	public AbstractDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		this.daoType = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void save(E e) {
		entityManager.persist(e);
	}

	public void update(E e) {
		entityManager.merge(e);
	}

	public void remove(Long id) {
		entityManager.remove(entityManager.getReference(daoType, id));
	}

	public List<E> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<E> criteria = builder.createQuery(daoType);

		Root<E> root = criteria.from(daoType);

		criteria.select(root);

		TypedQuery<E> query = entityManager.createQuery(criteria);

		return query.getResultList();
	}

	public E findById(Long i) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<E> criteria = builder.createQuery(daoType);

		Root<E> root = criteria.from(daoType);

		criteria.select(root).where(builder.equal(root.get("id"), i));

		TypedQuery<E> query = entityManager.createQuery(criteria);

		E e = null;

		try {
			e = query.getSingleResult();
		} catch (NoResultException ex) {
		}

		return e;

	}

}
