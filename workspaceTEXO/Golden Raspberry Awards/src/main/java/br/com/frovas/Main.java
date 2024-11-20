package br.com.frovas;

import java.io.FileReader;
import java.util.List;

import br.com.frovas.dto.FinalProducerIntervalDTO;
import br.com.frovas.pojo.Movie;
import br.com.frovas.pojo.Producer;
import br.com.frovas.webservice.GRAResource;

public class Main {

	public static void main(String[] args) {

		try {


			//criando base de dados
			DatabaseController.createMemoryDatabase();

//			facade.readCSVFile(new File("movielist.csv"));

			//lendo arquivo CSV
			List<Movie> movies = CSVController.readCSVFileToObject(new FileReader("movielistTotal.csv"));
//			movies.forEach(System.out::println);

			//salvando objetos lidos no CSV
			for (Movie movie : movies) {
//				DatabaseController.insertMovie(movie);

				int index = 0;
				for (Producer prod : movie.getProducers()) {
					Producer prodAux = ProducerController.getProducerByName(prod.getName());
					if( prodAux == null) {
						ProducerController.insertProducer(prod);
					} else {
						//trocar o produtor da lista pelo salvo para ter o id

						movie.getProducers().set(index, prodAux);

					}
					index++;
				}
				MovieController.insertMovie(movie);
			}

//			List<Movie> listMovies = DatabaseControler.listMoviesByYear(2016);


			List<Movie> listMovies = MovieController.listMoviesFull();
			System.out.println("Lista de filmes");
			for (Movie movie : listMovies) {
				System.out.println(movie.toString());
			}

//			List<Movie> listWinnerMovies = DatabaseController.listWinnerMovies();


			List<Movie> listWinnerMovies = MovieController.listWinnerMovies();
			System.out.println("Lista de filmes Vencedores");
			for (Movie movie : listWinnerMovies) {
				System.out.println(movie.toString());
			}


			System.out.println("RESULTADO FINAL");
			FinalProducerIntervalDTO finalDTO = GRAController.listMinMaxWinners();
			System.out.println(finalDTO.fullPrint());


			System.out.println("RESULTADO FINAL JSON");
			GRAResource graResource = new GRAResource();
			graResource.listMinMaxWinners();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
