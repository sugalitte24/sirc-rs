package co.gov.movilidadbogota.sircrs.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityRequest {

    //@NotBlank
    private String user;
    //@NotBlank
    private String password;

    private Long tipoDocumento;
    private Long numeroDocumento;

}
