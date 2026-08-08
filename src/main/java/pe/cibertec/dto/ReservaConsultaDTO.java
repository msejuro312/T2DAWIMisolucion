package pe.cibertec.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservaConsultaDTO {

    private Integer numReserva;
    private LocalDate fechaReserva;
    private String nomCliente;
    private String nomTipoHabitacion;
    private Double precio;

}
