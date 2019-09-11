package br.com.devmedia.curso.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.curso.entities.TipoSexo;
import br.com.devmedia.curso.entities.Usuario;
import br.com.devmedia.curso.exception.NaoExisteDAOException;
import net.bytebuddy.implementation.bytecode.Throw;

@Repository
@Transactional
public class UsuarioDAO implements UsuarioInterfaceDAO{

    //PersistenceContext é a injeção de dependencias para o funcionanmento da entityManager
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Override 
    public void update(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(entityManager.getReference(Usuario.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);

		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(root).where(builder.equal(root.get("id"), id));

		TypedQuery<Usuario> query = entityManager.createQuery(criteria);

		try {
			return query.getSingleResult();
		}
		catch (NoResultException ex) {
            throw new NaoExisteDAOException("Não existe Registro");
        }
    }

    //Essa Transactional informa para o gerenciador que este metodo, é apenas leitura. Por isso o readOnly
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = criteria.from(Usuario.class);
		
		criteria.select(root);
		
		TypedQuery<Usuario> query = entityManager.createQuery(criteria);
        
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findBySexo(TipoSexo sexo) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);

		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(root).where(builder.equal(root.get("sexo"), sexo));

		TypedQuery<Usuario> query = entityManager.createQuery(criteria);
	
		return query.getResultList();
	}

    @Override
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