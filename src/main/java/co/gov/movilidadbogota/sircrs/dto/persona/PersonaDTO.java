package co.gov.movilidadbogota.sircrs.dto.persona;

import java.util.Date;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDTO {

    private Long id;
    private Long tipoIdentificacion;
    private String tipoIdentificacionDesc;
    private Long numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private Long celular;
    private String direccion;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaExpedicionDocumento;
    private Long genero;

}
