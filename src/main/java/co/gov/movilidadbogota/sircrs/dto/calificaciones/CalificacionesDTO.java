package co.gov.movilidadbogota.sircrs.dto.calificaciones;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalificacionesDTO {

    private String nivelSatisfaccion;
    private String valoracion;
    private String felicitacion = "";
    private String buenTrabajo = "";
    private String conduccionSegura = "";
    private String siguioRuta = "";
    private String vehiculoBueno = "";
    private String malServicio = "";
    private String cobroInadecuado = "";
    private String noSigueRuta = "";
    private String malVehiculo = "";
    private String tratoConductor = "";
    private String acosoDiscriminacion = "";
    private String prestacionIncompleta = "";
    private String manejoAgresivo = "";
    private String informacionIrregular = "";
    private String otro = "";
}
