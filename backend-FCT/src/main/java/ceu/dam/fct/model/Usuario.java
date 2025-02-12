package ceu.dam.fct.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Usuario {
	private Long id;
	private String nombreUsuario;
	private String password;
	private Perfil perfil;
	private Perfil asociado;
	private Boolean activo;
	
}
