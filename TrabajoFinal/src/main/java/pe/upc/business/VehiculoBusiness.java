package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Vehiculo;
import pe.upc.model.repository.VehiculoRepository;

@Named
public class VehiculoBusiness implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private VehiculoRepository vehiculoRepository;

	@Transactional
	public Long insert(Vehiculo vehiculo) throws Exception {
		return vehiculoRepository.insert(vehiculo);
	}

	
	@Transactional
	public Long update(Vehiculo vehiculo) throws Exception{
		return vehiculoRepository.update(vehiculo);
	}
	
	
	public List<Vehiculo> getAll() throws Exception {
		return vehiculoRepository.findAll();
	}
	
	
	public List<Vehiculo> getVehiculosByName(String name) throws Exception{
		return vehiculoRepository.findByName(name);
	}

}
