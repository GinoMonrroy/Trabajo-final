package pe.upc.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.UsuarioBusiness;
import pe.upc.model.entity.Usuario;
import pe.upc.util.Message;

@Named
@SessionScoped
public class UsuarioController implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Inject
	private UsuarioBusiness usuarioBusiness;
	

	private Usuario usuario;
	private List<Usuario> usuarios;
	private Usuario usuarioSelect;
	private String filterName;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarios = new ArrayList<>();

		getAllUsuarios();
	}
	public void getAllUsuarios() {
		try {
			usuarios = usuarioBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Usuarios :" + e.getMessage());
		}
	}
	

	public String listUsuario() {
		return "listUsuario.xhtml";
	}
	public String saveUsuario() {
		String view = "";
		try {

			if (usuario.getUsuario_id() != null) {
				usuarioBusiness.update(usuario);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				usuarioBusiness.insert(usuario);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllUsuarios();
			resetForm();
			view = "listUsuario";
		} catch (Exception e) {
			Message.messageError("Error Usuario :" + e.getStackTrace());
		}

		return view;
	}
	public String editUsuario() {
		String view = "";
		try {
			if (this.usuarioSelect != null) {
				this.usuario = usuarioSelect;

				view = "/usuario/updateUsuario";
			} else {
				Message.messageInfo("Debe seleccionar un usuario");
			}
		} catch (Exception e) {
			Message.messageError("Error Usuario :" + e.getMessage());
		}

		return view;
	}
	public void searchUsuarioByName() {
		try {

			usuarios = usuarioBusiness.getUsuariosByName(this.filterName.trim());
			resetForm();
			if (usuarios.isEmpty()) {
				Message.messageInfo("No se encontraron usuarios");

			}

		} catch (Exception e) {
			Message.messageError("Error Usuario Search :" + e.getMessage());
		}
	}
	public void selectUsuario(SelectEvent e) {
		this.usuarioSelect = (Usuario) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.usuario = new Usuario();
	}
	public UsuarioBusiness getUsuarioBusiness() {
		return usuarioBusiness;
	}
	public void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuarioSelect() {
		return usuarioSelect;
	}
	public void setUsuarioSelect(Usuario usuarioSelect) {
		this.usuarioSelect = usuarioSelect;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
