package pe.cibertec.controllers;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.dto.ReservaConsultaDTO;
import pe.cibertec.entities.Reserva;
import pe.cibertec.service.ReservaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(("/api/sejuro"))
public class ReservaController {

    private  final ReservaService reservaService;

    public ReservaController (ReservaService reservaService){
        this.reservaService = reservaService;
    }

    //listado de reservas
    @GetMapping
    public List<Reserva>  listarReservas(){
        return reservaService.listarReservas();
    }

    //obtener reserva
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(@PathVariable Integer id){
        return reservaService.obtenerReserva(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //agregarReserva
    @PostMapping
    public ResponseEntity<Reserva> agregarReserva(@RequestBody Reserva reserva){
        Reserva registrada = reservaService.agregarReserva(reserva);
        return new ResponseEntity<>(registrada, HttpStatus.CREATED);
    }

    //borrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> eliminarReserva(@PathVariable Integer id){
        return reservaService.eliminarReserva(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //consulta
    @GetMapping ("/consultar")
    public ResponseEntity<List<ReservaConsultaDTO>> consultarPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fecha){
        List<ReservaConsultaDTO> resultados = reservaService.consultarPorFecha(fecha);
        if(resultados.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultados);
    }

}
