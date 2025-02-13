package ceu.dam.fct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.Fecha;
import ceu.dam.fct.model.RegistroPractica;

@Repository
public interface RegistroPracticaRepository extends JpaRepository<RegistroPractica, Long> {

	public List<RegistroPractica> findRegistrosByAlumno(Alumno alumno, String fechaDesde, String fechaHasta, String filtro);

	public boolean existsByAlumnoAndFecha(Alumno alumno, Fecha fecha);

}
