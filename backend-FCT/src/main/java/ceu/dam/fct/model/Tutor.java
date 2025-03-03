package ceu.dam.fct.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
public class Tutor extends Perfil {

	@OneToMany(mappedBy = "tutorDocente")
	@JsonBackReference
	private List<Alumno> alumnos; // Relación con Alumno	
	
}