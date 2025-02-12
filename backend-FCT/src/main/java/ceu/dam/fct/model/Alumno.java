package ceu.dam.fct.model;

import java.util.List;

import jakarta.persistence.Entity;
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
	private int anioCurso;

	@ManyToOne
	@JoinColumn(name = "tutor_docente_id")
	private Tutor tutorDocente; // Relación con TutorDocente

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa; // Relación con Empresa

	@OneToOne(mappedBy = "usuarioAsociado")
	private Usuario usuario; // Relación con Usuario

	@OneToMany(mappedBy = "alumno")
	private List<RegistroPractica> registrosPractica; // Relación con RegistroPractica

}
