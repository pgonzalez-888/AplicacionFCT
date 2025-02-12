package ceu.dam.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombreUsuario;
	private String contrasena;
	private String perfil; // Alumno, Tutor

	@OneToOne
	@JoinColumn(name = "alumno_id")
	private Alumno usuarioAsociado; // Relación con Alumno

	private Boolean activo;

}
