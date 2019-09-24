package pe.upc.business;

import java.io.Serializable;
import java.util.List;


import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Usuario;
import pe.upc.model.repository.UsuarioRepository;

@Named
public class UsuarioBusiness implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	
	public List<Usuario> getAll() throws Exception {
		return usuarioRepository.findAll();
	}
	@Transactional
	public Long insert(Usuario usuario) throws Exception {
		return usuarioRepository.insert(usuario);
	}
	
	public Long update(Usuario usuario) throws Exception{
		return usuarioRepository.update(usuario);
	}
	public List<Usuario> getUsuariosByName(String name) throws Exception{
		return usuarioRepository.findByName(name);
	}
}
