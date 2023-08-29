package co.gov.movilidadbogota.sircrs.dto.pqr;

import co.gov.movilidadbogota.sircrs.dto.calificaciones.CalificacionesDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PqrRequestDTO {

    private Integer tipoIdentificacion;
    private Integer numeroIdentificacion;
    private String genero;
    private String nombres;
    private String municipio;
    private String ciudad;
    private String correoElectronico;
    private String telefonoFijo;
    private PqrsDTO pqr;
    private CalificacionesDTO calificacion;
}
