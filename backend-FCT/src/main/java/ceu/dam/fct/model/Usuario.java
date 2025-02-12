package ceu.dam.fct.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Usuario {
	private Integer id;
	private String nombreUsuario;
	private String password;
	private String perfil;
	private Perfil asociado;
	private Boolean activo;
	
}
