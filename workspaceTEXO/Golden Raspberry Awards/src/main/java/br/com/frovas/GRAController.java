package br.com.frovas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import br.com.frovas.dto.FinalProducerIntervalDTO;
import br.com.frovas.dto.ProducerDTO;
import br.com.frovas.dto.ProducerIntervalDTO;
import br.com.frovas.pojo.Movie;
import br.com.frovas.pojo.Producer;

public class GRAController {


	public static FinalProducerIntervalDTO listMinMaxWinners() throws Exception {


//		obter somente produtores vencedores e jogar numa DTO
//		arrumar a DTO com produtor, filme e ano que venceu
//		ver como fazer isso direto na ProducerController

		List<ProducerDTO> listProducerDTO = new ArrayList<ProducerDTO>();
		List<Movie> listWinnerMovies = MovieController.listWinnerMovies();

		for (Movie movie : listWinnerMovies) {
			ProducerDTO dto = new ProducerDTO();
			for (Producer producer: movie.getProducers()) {
				dto.setProducerName(producer.getName());
				dto.setMovie(movie.getTitle());
				dto.setYear(movie.getYear());

				listProducerDTO.add(dto);
			}
		}


//		fazer uma HASH onde produtor é a chave?
		HashMap<String, List<ProducerDTO>> hashProducerDTO = new HashMap<String, List<ProducerDTO>>();

		for (ProducerDTO dto : listProducerDTO) {
			if(!hashProducerDTO.containsKey(dto.getProducerName())) {
				List<ProducerDTO> listAux = new ArrayList<ProducerDTO>();
				listAux.add(dto);
				hashProducerDTO.put(dto.getProducerName(), listAux);
			} else {
				hashProducerDTO.get(dto.getProducerName()).add(dto);
			}
		}

//		percorrer a lista por produtor, vendo a diferença de anos utilizando duas DTOs auxiliares, máxima e minima
//		substituindo a medida que encontrar outro

		ProducerIntervalDTO dtoMin = new ProducerIntervalDTO();
		dtoMin.setInterval(9999);
		ProducerIntervalDTO dtoMax = new ProducerIntervalDTO();
		dtoMax.setInterval(0);

		for (String producerName : hashProducerDTO.keySet()) {

			List<ProducerDTO> listProducersDTO = new ArrayList<>();
			listProducersDTO.addAll(hashProducerDTO.get(producerName));
			//se o produtor tem só um filme vencedor, nem precisa comparar
			if(listProducersDTO.size() > 1) {

				Comparator<ProducerDTO> comp = new Comparator<ProducerDTO>() {
											  @Override
											  public int compare(ProducerDTO dto1, ProducerDTO dto2) {
											    return dto1.getYear().compareTo(dto2.getYear());
											  }
											};
				Collections.sort(listProducersDTO,  comp);
				// o máximo de diferença de anos será o primeiro da lista com o último
				Integer firstYear = listProducersDTO.get(0).getYear();
				Integer lastYear = listProducersDTO.get(listProducersDTO.size()-1).getYear();
				if(lastYear - firstYear > dtoMax.getInterval()) {
					dtoMax.setProducer(producerName);
					dtoMax.setInterval(lastYear - firstYear);
					dtoMax.setPreviousWin(firstYear);
					dtoMax.setFollowingWin(lastYear);
				}

				int count = 0;
				// percorrer comparando dois a dois
				while(count < listProducersDTO.size()-2) {

					Integer interval =  listProducersDTO.get(count+1).getYear() - listProducersDTO.get(count).getYear();
					if(interval > 0 && interval < dtoMin.getInterval()) {
						dtoMin.setProducer(producerName);
						dtoMin.setInterval(lastYear - firstYear);
						dtoMin.setPreviousWin(firstYear);
						dtoMin.setFollowingWin(lastYear);
					}
					count++;
				}
			}
		}

		FinalProducerIntervalDTO finalDTO = new FinalProducerIntervalDTO();
		List<ProducerIntervalDTO> min = new ArrayList<ProducerIntervalDTO>();
		min.add(dtoMin);
		List<ProducerIntervalDTO> max = new ArrayList<ProducerIntervalDTO>();
		max.add(dtoMax);
		finalDTO.setMin(min);
		finalDTO.setMax(max);

		return finalDTO;
	}
}
