package ceu.dam.fct.model;

import java.util.List;

import jakarta.persistence.Entity;
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
	private Integer id;

	private java.util.Date fecha;
	private Integer anioCurso;
	private String evaluacion; // Septiembre, Marzo

	@OneToMany(mappedBy = "fecha")
	private List<RegistroPractica> registrosPractica; // Relación con RegistroPractica

}
