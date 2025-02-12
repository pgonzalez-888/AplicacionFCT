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
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombreEmpresa;
	private String tutorLaboral;
	private String emailTutorLaboral;
	private String telefonoTutorLaboral;
	private boolean activo;

	@OneToMany(mappedBy = "empresa")
	private List<Alumno> alumnos;

}
