package app.daos.implementations;

import javax.persistence.EntityManager;

import app.daos.interfaces.MovimientoDao;
import app.models.Movimiento;

public class MovimientoDaoImpl extends BaseDaoImpl<Movimiento> implements MovimientoDao {

	public MovimientoDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	protected Class<Movimiento> getTypeClass() {
		return Movimiento.class;
	}

	@Override
	protected boolean isCreated(Movimiento movimiento) {
		return movimiento.getId() != null;
	}

	@Override
	protected String getFindAllNamedQuery() {
		return "findAllMovimientos";
	}

}
