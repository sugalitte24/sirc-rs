package co.gov.movilidadbogota.sircrs.dto.pqr;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PqrsDTO {
    private String hechos;
    private String asunto;
    private Long idConductor;
}
