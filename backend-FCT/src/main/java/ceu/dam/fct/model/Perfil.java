package ceu.dam.fct.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String nombreCompleto;
	protected Boolean activo;
}
