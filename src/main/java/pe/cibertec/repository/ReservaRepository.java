package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.dto.ReservaConsultaDTO;
import pe.cibertec.entities.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {


    @Query("SELECT new pe.cibertec.dto.ReservaConsultaDTO(r.numReserva,r.fechaReserva,c.nomCliente,t.nomTipoHabitacion,t.precio) "+"FROM Reserva r JOIN r.cliente c JOIN r.tipoHabitacion t "+"WHERE r.fechaReserva = :fecha")
    List<ReservaConsultaDTO> consultarPorFecha(@Param("fecha")LocalDate fecha);


}
