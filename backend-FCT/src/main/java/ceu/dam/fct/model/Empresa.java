package ceu.dam.fct.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private Long id;

	private String nombreEmpresa;
	private String tutorLaboral;
	private String emailTutorLaboral;
	private String telefonoTutorLaboral;
	private Boolean activo;

	@OneToMany(mappedBy = "empresa")
	@JsonBackReference
	private List<Alumno> alumnos;

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nombreEmpresa=" + nombreEmpresa + ", tutorLaboral=" + tutorLaboral
				+ ", emailTutorLaboral=" + emailTutorLaboral + ", telefonoTutorLaboral=" + telefonoTutorLaboral
				+ ", activo=" + activo + "]";
	}
	

}
