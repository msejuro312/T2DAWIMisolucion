package pe.cibertec.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numReserva;
    private LocalDate fechaReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private  Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cod_tipo")
    private TipoHabitacion tipoHabitacion;

    private String estado;




}
