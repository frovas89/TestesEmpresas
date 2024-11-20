package br.com.frovas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.frovas.pojo.Movie;

public class MovieController {


	public static void insertMovie(Movie movie) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(movie);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();


        System.out.println("Objeto Movie inserido na tabela: "+movie.toString());
	}

	public static void updateMovie(Movie movie) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(movie);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();


        System.out.println("Objeto Movie atualizado na tabela: "+movie.toString());
	}

	public static  List<Movie> listMoviesFull() {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		String queryStr =
			      "SELECT DISTINCT movie FROM Movie AS movie "
			      + "LEFT JOIN movie.producers ";
	  TypedQuery<Movie> query = entityManager.createQuery(queryStr, Movie.class);
	  List<Movie> results = query.getResultList();

	  return results;
	}

	public static  List<Movie> listWinnerMovies() {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GRA_DB");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		String queryStr =
			      "SELECT DISTINCT movie FROM Movie AS movie "
			      + "LEFT JOIN movie.producers "
			      + "WHERE movie.winner = :winner";
	  TypedQuery<Movie> query = entityManager.createQuery(queryStr, Movie.class);
	  query.setParameter("winner", Boolean.TRUE);
	  List<Movie> results = query.getResultList();

	  return results;
	}

}
