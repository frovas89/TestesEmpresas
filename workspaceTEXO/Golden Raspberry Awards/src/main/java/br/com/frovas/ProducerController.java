package br.com.frovas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.frovas.pojo.Producer;

public class ProducerController {

	public static void insertProducer(Producer producer) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(producer);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		System.out.println("Objeto producer inserido na tabela: " + producer.toString());
	}

	public static void updateProducer(Producer producer) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(producer);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		System.out.println("Objeto producer atualizado na tabela: " + producer.toString());
	}

	public static List<Producer> listProducersByName(String name) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		String queryStr = "SELECT DISTINCT producer FROM Producer AS producer WHERE producer.name LIKE '" + name + "'";
		TypedQuery<Producer> query = entityManager.createQuery(queryStr, Producer.class);
		List<Producer> results = query.getResultList();

		return results;
	}

	public static Producer getProducerByName(String name) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		String queryStr = "SELECT producer FROM Producer AS producer WHERE producer.name LIKE '" + name + "'";
		TypedQuery<Producer> query = entityManager.createQuery(queryStr, Producer.class);
		Producer result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

		return result;
	}

//	public static List<ProducerDTO> listWinnerProducersDTO(String name) {
//		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
//		final EntityManager entityManager = entityManagerFactory.createEntityManager();
//		String queryStr = "SELECT producer FROM Producer AS producer WHERE producer.name LIKE '" + name + "'";
//		TypedQuery<ProducerDTO> query = entityManager.createQuery(queryStr, ProducerDTO.class);
//		List<ProducerDTO> results = query.getResultList();
//
//		return results;
//	}


}
