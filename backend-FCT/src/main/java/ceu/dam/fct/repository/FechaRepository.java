package ceu.dam.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.fct.model.Fecha;

@Repository
public interface FechaRepository extends JpaRepository<Fecha, Long> {

}
