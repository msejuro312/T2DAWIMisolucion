package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
