package ceu.dam.fct.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Fecha {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate fecha;
	private Integer anioCurso;
	private String evaluacion; // Septiembre, Marzo

	@OneToMany(mappedBy = "fecha",fetch = FetchType.EAGER)
	@JsonBackReference
	private List<RegistroPractica> registrosPractica; // Relación con RegistroPractica

	@Override
	public String toString() {
		return "Fecha [id=" + id + ", fecha=" + fecha + ", anioCurso=" + anioCurso + ", evaluacion=" + evaluacion + "]";
	}

}
