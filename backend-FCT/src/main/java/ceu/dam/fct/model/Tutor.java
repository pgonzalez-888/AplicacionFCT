package ceu.dam.fct.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tutor extends Perfil {

	@OneToMany(mappedBy = "tutorDocente")
	private List<Alumno> alumnos; // Relación con Alumno
}