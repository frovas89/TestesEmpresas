package br.com.frovas.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_movie")
public class Movie {

	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_movie_pk_sequence")
	@SequenceGenerator(name = "tb_movie_pk_sequence", sequenceName = "tb_movie_pk_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

//	@CsvBindByPosition(position = 0)
	@Column(name="m_year")
	private Integer year;

//	@CsvBindByPosition(position = 1)
	@Column(name="m_title")
	private String title;

//	private Studio studios;
	@ManyToMany
	@JoinTable(
			  name = "rl_movie_producer",
			  joinColumns = @JoinColumn(name = "id_movie"),
			  inverseJoinColumns = @JoinColumn(name = "id_producer"))
	private List<Producer> producers;

//	@CsvBindByPosition(position = 4)
	@Column(name="m_winner")
	private Boolean winner;

	@Override
	public String toString() {
		return "Movie [ " + this.id + ", " + this.year + ", " + this.title + ", " + this.producers.toString() + ", "
				+ this.winner + "] ";
	}

	/*
	 * Getters and Setters
	 */

	public Integer getYear() {
		return year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

}
