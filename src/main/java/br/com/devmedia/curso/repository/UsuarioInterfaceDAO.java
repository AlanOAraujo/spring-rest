package br.com.devmedia.curso.repository;

import java.util.List;

import br.com.devmedia.curso.entities.TipoSexo;
import br.com.devmedia.curso.entities.Usuario;

public interface UsuarioInterfaceDAO {

    void save(Usuario usuario);

    void update(Usuario usuario);
    
    void remove(Long id);

    Usuario findById(Long id);

    List<Usuario> findAll();
    
    List<Usuario> findBySexo(TipoSexo sexo);

    List<Usuario> findByNome(String nome);
    
}