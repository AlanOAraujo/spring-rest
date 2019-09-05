package br.com.devmedia.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmedia.curso.entities.TipoSexo;
import br.com.devmedia.curso.entities.Usuario;
import br.com.devmedia.curso.repository.UsuarioInterfaceDAO;

/**
 * UsuarioService
 */
//Essa Annotation, marca a classe como um bean gerenciavel pelo Spring, mais do tipo Regra de Negócio
@Service
public class UsuarioService implements UsuarioInterfaceService{

    @Autowired
    private UsuarioInterfaceDAO usuarioDAO;
    
    @Override
    public void salvar(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public void editar(Long id, Usuario usuario) {
        Usuario usuarioCadastrado = this.getId(id);

        usuarioCadastrado.setNome(usuario.getNome());
        usuarioCadastrado.setNomeMae(usuario.getNomeMae());
        usuarioCadastrado.setDtNascimento(usuario.getDtNascimento());
        usuarioCadastrado.setSexo(usuario.getSexo());

        usuarioDAO.update(usuarioCadastrado);
    }

    @Override
    public void excluir(Long id) {
        usuarioDAO.remove(id);
    }

    @Override
    public Usuario getId(Long id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public List<Usuario> getTodos() {
        return usuarioDAO.findAll();
    }

    @Override
    public List<Usuario> getBySexo(TipoSexo sexo) {
        return usuarioDAO.findBySexo(sexo);
    }

    @Override
    public List<Usuario> getByNome(String nome) {
        return usuarioDAO.findByNome(nome);
    }
    
}