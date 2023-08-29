package co.gov.movilidadbogota.sircrs.service.security;

import co.gov.movilidadbogota.sircrs.dto.security.SecurityRequest;
import co.gov.movilidadbogota.sircrs.dto.security.TokenDto;

public interface SecurityService {

    TokenDto getJWTToken( SecurityRequest request );
}
