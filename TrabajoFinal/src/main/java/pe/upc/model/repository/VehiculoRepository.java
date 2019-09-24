package pe.upc.model.repository;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Vehiculo;

@Named
public class VehiculoRepository implements Serializable{
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pwPU")
	private EntityManager em;
	
	public Long insert(Vehiculo vehiculo) throws Exception {
		em.persist(vehiculo);
		return vehiculo.getVehiculo_id();
	}
	
	public Long update(Vehiculo vehiculo) throws Exception {
		em.merge(vehiculo);
		return vehiculo.getVehiculo_id();
	}
	
	public List<Vehiculo> findAll() throws Exception {
		List<Vehiculo> vehiculos = new ArrayList<>();

		TypedQuery<Vehiculo> query = em.createQuery("FROM Vehiculo p", Vehiculo.class);
		vehiculos = query.getResultList();

		return vehiculos;
	}
	public List<Vehiculo> findByName(String name) throws Exception {
		List<Vehiculo> vehiculos = new ArrayList<>();

		TypedQuery<Vehiculo> query = em.createQuery("FROM Vehiculo p WHERE p.name LIKE ?1", Vehiculo.class);
		query.setParameter(1, "%" + name + "%");
		vehiculos = query.getResultList();

		return vehiculos;
	}
}
