package br.com.frovas.dto;

public class ProducerDTO {

	private String producerName;
	private String movie;
	private Integer year;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProducerDTO other = (ProducerDTO) obj;
		if (producerName == null) {
			if (other.producerName != null) {
				return false;
			}
		} else if (!producerName.equals(other.producerName)) {
			return false;
		}
		return true;
	}

	/*
	 * Getters and Setters
	 */

	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}


}
