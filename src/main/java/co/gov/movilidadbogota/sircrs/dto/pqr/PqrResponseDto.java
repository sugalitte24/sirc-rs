package co.gov.movilidadbogota.sircrs.dto.pqr;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PqrResponseDto {

    private String radicado;
    private String mensaje;
    private String error;
}
