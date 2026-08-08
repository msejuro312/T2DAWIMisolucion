package pe.cibertec.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.cibertec.dto.ReservaConsultaDTO;
import pe.cibertec.entities.Cliente;
import pe.cibertec.entities.Reserva;
import pe.cibertec.repository.ClienteRepository;
import pe.cibertec.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;


    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
    }

    //Listar reservas

    public List<Reserva> listarReservas(){
        return reservaRepository.findAll();
    }

    //devuelve reserva por id
    public Optional<Reserva> obtenerReserva(Integer numReserva){
        return reservaRepository.findById(numReserva);
    }

    //registra reserva (con validacion de si existe o no
    @Transactional
    public Reserva agregarReserva(Reserva reserva) {
        Cliente cliente = reserva.getCliente();
        if (cliente != null) {
            if (cliente.getIdCliente() != null) {
                //validación de si hay cliente con id
                Optional<Cliente> existente = clienteRepository.findById(cliente.getIdCliente());
                if (existente.isPresent()) {
                    cliente = existente.get();
                } else {
                    cliente.setIdCliente(null);
                    cliente = clienteRepository.save(cliente);
                }
            }else{
                    cliente = clienteRepository.save(cliente);
                }
                reserva.setCliente(cliente);
            }

            reserva.setEstado("A");
            return reservaRepository.save(reserva);
        }


    //borrado lógico
        @Transactional
        public Optional<Reserva> eliminarReserva (Integer numReserva){
            return reservaRepository.findById(numReserva)
                    .map(reserva ->{
                        reserva.setEstado("I");
                        return reservaRepository.save(reserva);
                    });
        }

    //consulta por fecha
    public List<ReservaConsultaDTO> consultarPorFecha(LocalDate fecha){
        return reservaRepository.consultarPorFecha(fecha);
    }
}
