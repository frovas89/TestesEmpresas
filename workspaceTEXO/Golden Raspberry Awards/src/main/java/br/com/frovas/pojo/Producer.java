package br.com.frovas.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_producer")
public class Producer {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_producer_pk_sequence")
	@SequenceGenerator(name = "tb_producer_pk_sequence", sequenceName = "tb_producer_pk_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name="p_name")
	private String name;

	@ManyToMany(mappedBy = "producers")
	List<Movie> movies;

	@Override
	public String toString() {
		return "P: [" + this.id + ", " + this.name + "]";
	}

	/*
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
