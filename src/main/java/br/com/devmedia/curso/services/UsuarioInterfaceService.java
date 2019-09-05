package br.com.devmedia.curso.services;

import java.util.List;

import br.com.devmedia.curso.entities.TipoSexo;
import br.com.devmedia.curso.entities.Usuario;

/**
 * UsuarioInterfaceService
 */
public interface UsuarioInterfaceService {

    void salvar(Usuario usuario);
 
    void editar(Long id, Usuario usuario);
 
    void excluir(Long id);
 
    Usuario getId(Long id);
 
    List<Usuario> getTodos();

    List<Usuario> getBySexo(TipoSexo sexo);

    List<Usuario> getByNome(String nome);

}