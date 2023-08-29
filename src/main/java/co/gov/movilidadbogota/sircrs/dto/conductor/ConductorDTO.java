package co.gov.movilidadbogota.sircrs.dto.conductor;

import co.gov.movilidadbogota.sircrs.dto.persona.PersonaDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConductorDTO {

    private long id;
    private String grupoSanguineo;
    private String factorRh;
    private long idEps;
    private long idArl;
    private long idAfp = -1;
    private String uriFoto;
    private String foto;
    private PersonaDTO persona;
}
