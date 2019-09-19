package br.com.devmedia.curso.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmedia.curso.entities.TipoSexo;
import br.com.devmedia.curso.entities.Usuario;
import br.com.devmedia.curso.exception.IdNaoValidoServiceException;
import br.com.devmedia.curso.repository.UsuarioInterfaceDAO;

/**
 * UsuarioService
 */
// Essa Annotation, marca a classe como um bean gerenciavel pelo Spring, mais do
// tipo Regra de Negócio
@Service
public class UsuarioService implements UsuarioInterfaceService {

    @Autowired
    private UsuarioInterfaceDAO usuarioDAO;

    @Override
    public void salvar(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public void editar(Long id, Usuario usuario) {
        Usuario usuarioCadastrado = this.getId(idValido(id));

        usuarioCadastrado.setNome(usuario.getNome());
        usuarioCadastrado.setNomeMae(usuario.getNomeMae());
        usuarioCadastrado.setDtNascimento(usuario.getDtNascimento());
        usuarioCadastrado.setSexo(usuario.getSexo());

        usuarioDAO.update(usuarioCadastrado);
    }

    @Override
    public void excluir(Long id) {
        usuarioDAO.remove(idValido(id));
    }

    @Override
    public Usuario getId(Long id) {
        return usuarioDAO.findById(idValido(id));
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

    @Override
    public Usuario updateDtNascimento(Long id, LocalDate dt_nascimento) {
        
        Usuario usuario = usuarioDAO.findById(idValido(id));

        usuario.setDtNascimento(dt_nascimento);

        this.editar(id, usuario);

        return usuario;
    }

    private Long idValido(Long id){

        if(id <= 0){
            throw new IdNaoValidoServiceException("Valor do campo id está invalido." +
            "Deve ser um valor inteiro maior que zero");
        }
        return id;
    }
    
}