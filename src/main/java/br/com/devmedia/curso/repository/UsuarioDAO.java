package br.com.devmedia.curso.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.curso.entities.TipoSexo;
import br.com.devmedia.curso.entities.Usuario;

@Repository
@Transactional
public class UsuarioDAO extends AbstractDAO<Usuario> {

	
	public UsuarioDAO() {
		super();
	}
	
	public UsuarioDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	// PersistenceContext é a injeção de dependencias para o funcionanmento da
	// entityManager

	/*
	 * @PersistenceContext private EntityManager entityManager;
	 * 
	 * @Override public void save(Usuario usuario) { entityManager.persist(usuario);
	 * }
	 * 
	 * @Override public void update(Usuario usuario) { entityManager.merge(usuario);
	 * }
	 * 
	 * @Override public void remove(Long id) { try {
	 * entityManager.remove(entityManager.getReference(Usuario.class, id)); } catch
	 * (EntityNotFoundException ex) { throw new
	 * NaoExisteDAOException("Não existe Registro com o id "+ id); } }
	 * 
	 * @Override
	 * 
	 * @Transactional(readOnly = true) public Usuario findById(Long id) {
	 * 
	 * CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	 * 
	 * CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
	 * 
	 * Root<Usuario> root = criteria.from(Usuario.class);
	 * 
	 * criteria.select(root).where(builder.equal(root.get("id"), id));
	 * 
	 * TypedQuery<Usuario> query = entityManager.createQuery(criteria);
	 * 
	 * try { return query.getSingleResult(); } catch (NoResultException ex) { throw
	 * new NaoExisteDAOException("Não existe Registro com o id "+ id); } }
	 * 
	 * //Essa Transactional informa para o gerenciador que este metodo, é apenas
	 * leitura. Por isso o readOnly
	 * 
	 * @Override
	 * 
	 * @Transactional(readOnly = true) public List<Usuario> findAll() {
	 * 
	 * CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	 * 
	 * CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
	 * 
	 * Root<Usuario> root = criteria.from(Usuario.class);
	 * 
	 * criteria.select(root);
	 * 
	 * TypedQuery<Usuario> query = entityManager.createQuery(criteria);
	 * 
	 * return query.getResultList(); }
	 */

	@Transactional(readOnly = true)
	public List<Usuario> findBySexo(TipoSexo sexo) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);

		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(root).where(builder.equal(root.get("sexo"), sexo));

		TypedQuery<Usuario> query = entityManager.createQuery(criteria);

		return query.getResultList();
	}


	@Transactional(readOnly = true)
	public List<Usuario> findByNome(String nome) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);

		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(root).where(builder.like(root.get("nome"), "%" + nome + "%"));

		TypedQuery<Usuario> query = entityManager.createQuery(criteria);

		return query.getResultList();

	}

}