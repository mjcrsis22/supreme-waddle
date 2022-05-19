package app.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import app.daos.implementations.ClienteDaoImpl;
import app.models.Cliente;
import app.models.Direccion;

public class UsaApp {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miniBankPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Direccion d = new Direccion("", "", "", "", "", "", "");
		Cliente c = new Cliente("Danilo Jose", "Guerrero", d, "1123234545", "dguerrero@minibank.com");

		ClienteDaoImpl clienteDao = new ClienteDaoImpl(em);

		tx.begin();
		clienteDao.save(c);
		tx.commit();

		em.close();
		emf.close();
	}
}
