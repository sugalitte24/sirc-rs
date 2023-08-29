package co.gov.movilidadbogota.sircrs.service.security.impl;

import co.gov.movilidadbogota.sircrs.dto.security.SecurityRequest;
import co.gov.movilidadbogota.sircrs.dto.security.TokenDto;
import co.gov.movilidadbogota.sircrs.model.PersonaEntity;
import co.gov.movilidadbogota.sircrs.model.TipoDocumentoEntity;
import co.gov.movilidadbogota.sircrs.model.UsuarioEntity;
import co.gov.movilidadbogota.sircrs.repository.PersonaRepository;
import co.gov.movilidadbogota.sircrs.repository.TipoDocumentoRepository;
import co.gov.movilidadbogota.sircrs.repository.UsuarioRepository;
import co.gov.movilidadbogota.sircrs.service.security.SecurityService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Value("${security.jwt.secret}")
    private String SECRET;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public Optional<UsuarioEntity> getUser( Long tipoDocumento, Long numeroDocumento ) {
        TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentoRepository.findById(tipoDocumento).get();
        PersonaEntity persona = personaRepository.findByNumeroDocumentoAndTipoDocumento(numeroDocumento, tipoDocumentoEntity);
        return Optional.ofNullable(usuarioRepository.findByPersonaId(persona.getId()));
    }

    @Override
    public TokenDto getJWTToken( SecurityRequest request ) {

        final Instant now = Instant.now();
        TokenDto tokenDto = new TokenDto();
        Optional<UsuarioEntity> userFind = getUser(request.getTipoDocumento(), request.getNumeroDocumento());

        if (userFind.isPresent()) {

            String token = Jwts
                    .builder()
                    .setId("SDM_PQRS")
                    .setSubject(request.getUser())
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

            tokenDto.setToken("Bearer " + token);
            tokenDto.setStartDate(Date.from(now));
            tokenDto.setExpirationDate(Date.from(now.plus(1, ChronoUnit.DAYS)));
            tokenDto.setTypeToken("Bearer");
            return tokenDto;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
