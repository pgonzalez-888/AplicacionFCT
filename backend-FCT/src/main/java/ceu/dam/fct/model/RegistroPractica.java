package ceu.dam.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class RegistroPractica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "alumno_id")
	private Alumno alumno; // Relación con Alumno

	@ManyToOne
	@JoinColumn(name = "fecha_id")
	private Fecha fecha; // Relación con Fecha

	private int horas;
	private String descripcion;

}