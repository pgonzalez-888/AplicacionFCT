package ceu.dam.fct.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Alumno extends Perfil {
	private String ciclo; // DAM, DAW, ASIR
	private String evaluacion; // Septiembre, Marzo
	private Integer anioCurso;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "tutor_docente_id")
	@JsonManagedReference
	private Tutor tutorDocente; // Relación con TutorDocente

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id")
	@JsonManagedReference
	private Empresa empresa; // Relación con Empresa

	@OneToOne(mappedBy = "usuarioAsociado")
	@JsonBackReference
	private Usuario usuario; // Relación con Usuario

	@OneToMany(mappedBy = "alumno",fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<RegistroPractica> registrosPractica; // Relación con RegistroPractica

	@Override
	public String toString() {
		return "Alumno [ciclo=" + ciclo + ", evaluacion=" + evaluacion + ", anioCurso=" + anioCurso + ", tutorDocente="
				+ tutorDocente + ", empresa=" + empresa + ", usuario=" + usuario + ", registrosPractica="
				+ registrosPractica + "]";
	}
	
	
}
