package ceu.dam.fct.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.Fecha;
import ceu.dam.fct.model.RegistroPractica;

@Repository
public interface RegistroPracticaRepository extends JpaRepository<RegistroPractica, Long> {

	// Encontrar registros por alumno y por rango de fechas
    List<RegistroPractica> findByAlumnoAndFecha_FechaBetween(Alumno alumno, LocalDate fechaDesde, LocalDate fechaHasta);

    List<RegistroPractica> findByFecha_FechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);
    
    // Comprobar si existen registros entre las fechas para el alumno
    boolean existsByAlumnoAndFecha_Fecha(Alumno alumno, LocalDate fecha);

	public boolean existsByAlumnoAndFecha(Alumno alumno, Fecha fecha);

}
