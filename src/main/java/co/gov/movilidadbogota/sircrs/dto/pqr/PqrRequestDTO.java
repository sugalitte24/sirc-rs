package co.gov.movilidadbogota.sircrs.dto.pqr;

import co.gov.movilidadbogota.sircrs.dto.calificaciones.CalificacionesDTO;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PqrRequestDTO {

    private Integer tipoPeticionario;
    private Integer tipoIdentificacionUsuario;
    private Integer numeroIdentificacionUsuario;
    private String genero;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String municipio;
    private String direccion;
    private String correoElectronico;
    private String telefonoCelular;
    private Long idConductor;
    private String tarjetaControl;
    @NotEmpty(message = "Id Cache cannot be empty")
    private String idCache;
    @NotEmpty(message = "Placa Vehiculo cannot be empty")
    private String placaVehiculo;
    @NotEmpty(message = "Automático cannot be empty")
    private String automatico;
    private PqrsDTO pqr;
    private CalificacionesDTO calificacion;
}
