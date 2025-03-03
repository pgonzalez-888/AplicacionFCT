package ceu.dam.fct.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "registro_practica")
public class RegistroPractica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "alumno_id")
	@JsonBackReference
	private Alumno alumno; // Relación con Alumno

	@ManyToOne
	@JoinColumn(name = "fecha_id")
	@JsonManagedReference
	private Fecha fecha; // Relación con Fecha

	private Integer horas;
	private String descripcion;
	@Override
	public String toString() {
		return "RegistroPractica [id=" + id + ", alumno=" + alumno + ", fecha=" + fecha + ", horas=" + horas
				+ ", descripcion=" + descripcion + "]";
	}
	
	

}