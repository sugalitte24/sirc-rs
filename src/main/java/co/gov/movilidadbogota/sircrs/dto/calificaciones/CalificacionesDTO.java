package co.gov.movilidadbogota.sircrs.dto.calificaciones;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalificacionesDTO {
    private Integer nivelSatisfaccion;
    private String felicitacion;
    private Integer buenTrabajo = 0;
    private Integer conduccionSegura = 0;
    private Integer siguioRuta = 0;
    private Integer vehiculoBueno = 0;
    private Integer malServicio = 0;
    private Integer cobroInadecuado = 0;
    private Integer noSigueRuta = 0;
    private Integer malVehiculo = 0;
    private Integer tratoConductor = 0;
    private Integer acosoDiscriminacion = 0;
    private Integer prestacionIncompleta = 0;
    private Integer manejoAgresivo = 0;
    private Integer informacionIrregular = 0;
    private String otro;
    private String automatico;
}
