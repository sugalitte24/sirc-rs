package co.gov.movilidadbogota.sircrs.dto.security;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
    private Date startDate;
    private Date expirationDate;
    private String typeToken;
}
