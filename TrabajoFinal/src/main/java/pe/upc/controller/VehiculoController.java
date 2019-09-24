package pe.upc.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.VehiculoBusiness;
import pe.upc.business.UsuarioBusiness;
import pe.upc.model.entity.Vehiculo;
import pe.upc.model.entity.Usuario;
import pe.upc.util.Message;

@Named
@SessionScoped
public class VehiculoController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private VehiculoBusiness vehiculoBusiness;

	@Inject
	private UsuarioBusiness usuarioBusiness;

	private Vehiculo vehiculo;
	private List<Vehiculo> vehiculos;
	private Vehiculo vehiculoSelect;

	private Usuario usuario;
	private List<Usuario> usuarios;

	private String filterName;
	
	@PostConstruct
	public void init() {
		vehiculo = new Vehiculo();
		vehiculos = new ArrayList<Vehiculo>();

		usuario = new Usuario();
		usuarios = new ArrayList<>();

		getAllVehiculos();
	}
	
	public void getAllVehiculos() {
		try {
			vehiculos = vehiculoBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Vehiculos :" + e.getMessage());
		}
	}
	public String newVehiculo() {

		try {
			this.usuarios = usuarioBusiness.getAll();

			resetForm();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "insert.xhtml";
	}
	
	public String listVehiculo() {
		return "list.xhtml";
	}
	
	public String saveVehiculo() {
		String view = "";
		try {

			if (vehiculo.getVehiculo_id() != null) {
				vehiculo.setUsuario(usuario);
				vehiculoBusiness.update(vehiculo);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				vehiculo.setUsuario(usuario);
				vehiculoBusiness.insert(vehiculo);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllVehiculos();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Vehiculo :" + e.getStackTrace());
		}

		return view;
	}
	
	public String editVehiculo() {
		String view = "";
		try {
			if (this.vehiculoSelect != null) {
				this.vehiculo = vehiculoSelect;

				view = "/vehiculo/update";
			} else {
				Message.messageInfo("Debe seleccionar un vehiculo");
			}
		} catch (Exception e) {
			Message.messageError("Error Vehiculo :" + e.getMessage());
		}

		return view;
	}
	
	public void searchVehiculosByName() {
		try {

			vehiculos = vehiculoBusiness.getVehiculosByName(this.filterName.trim());
			resetForm();
			if (vehiculos.isEmpty()) {
				Message.messageInfo("No se encontraron vehiculos");

			}

		} catch (Exception e) {
			Message.messageError("Error Vehiculo Search :" + e.getMessage());
		}
	}
	
	public void selectVehiculo(SelectEvent e) {
		this.vehiculoSelect = (Vehiculo) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.vehiculo = new Vehiculo();
	}
	public VehiculoBusiness getVehiculoBusiness() {
		return vehiculoBusiness;
	}

	public void setVehiculoBusiness(VehiculoBusiness vehiculoBusiness) {
		this.vehiculoBusiness = vehiculoBusiness;
	}

	public UsuarioBusiness getUsuarioBusiness() {
		return usuarioBusiness;
	}

	public void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo getVehiculoSelect() {
		return vehiculoSelect;
	}

	public void setVehiculoSelect(Vehiculo vehiculoSelect) {
		this.vehiculoSelect = vehiculoSelect;
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
