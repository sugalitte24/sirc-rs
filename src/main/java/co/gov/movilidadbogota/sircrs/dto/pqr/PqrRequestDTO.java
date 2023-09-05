package co.gov.movilidadbogota.sircrs.dto.pqr;

import co.gov.movilidadbogota.sircrs.dto.calificaciones.CalificacionesDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PqrRequestDTO {

    private String tipoPeticionario;
    private Integer tipoIdentificacionUsuario;
    private Integer numeroIdentificacionUsuario;
    private String genero;
    private String nombres;
    private String apellidos;
    private String municipio;
    private String ciudad;
    private String direccion;
    private String correoElectronico;
    private String telefonoFijo;
    private String telefonoCelular;
    private Long idConductor;
    private String tarjetaControl;
    private String idCache;
    private String placaVehiculo;
    private String infoTarjeton;
    private String nombreConductor;
    private PqrsDTO pqr;
    private CalificacionesDTO calificacion;
}
