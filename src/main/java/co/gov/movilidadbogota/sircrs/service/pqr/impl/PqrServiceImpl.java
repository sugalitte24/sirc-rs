package co.gov.movilidadbogota.sircrs.service.pqr.impl;

import co.gov.movilidadbogota.sircrs.client.OrfeoClient;
import co.gov.movilidadbogota.sircrs.dto.calificaciones.CalificacionesMapper;
import co.gov.movilidadbogota.sircrs.dto.orfeo.OrfeoRequest;
import co.gov.movilidadbogota.sircrs.dto.orfeo.OrfeoResponse;
import co.gov.movilidadbogota.sircrs.dto.orfeo.VariablesOrfeo;
import co.gov.movilidadbogota.sircrs.dto.pqr.PqrMapper;
import co.gov.movilidadbogota.sircrs.dto.pqr.PqrRequestDTO;
import co.gov.movilidadbogota.sircrs.dto.pqr.PqrResponseDto;
import co.gov.movilidadbogota.sircrs.model.*;
import co.gov.movilidadbogota.sircrs.repository.*;
import co.gov.movilidadbogota.sircrs.service.pqr.PqrService;
import feign.FeignException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Qualifier("pqr")
public class PqrServiceImpl implements PqrService {
    @Autowired
    private PqrRepository pqrRepository;

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ParametroSimurRepository parametroSimurRepository;

    @Autowired
    private PeticionarioRepository peticionarioRepository;

    @Autowired
    private OrfeoClient orfeoClient;

    @Autowired
    private CalificacionesMapper calificacionesMapper;

    @Autowired
    private PqrMapper pqrMapper;

    @Value("${pqr.url.consulta.radicado}")
    private String URLConsultaRadicadoOrfeo;

    @Value("${pqr.url.orfeo}")
    private String URLOrfeo;

    @Override
    public PqrResponseDto createPqr( PqrRequestDTO request ) {
        try {
            PqrResponseDto response = new PqrResponseDto();

            if (request.getAutomatico().equals("Si")) {
                saveCalificacionEmpty(request);
                response.setMensaje("Calificación Guardada con Éxito");
                return response;
            }

            PeticionarioEntity peticionarioEntity = peticionarioRepository.findByNumeroIdentificacionUsuario(request.getNumeroIdentificacionUsuario());
            ConductorEntity conductor = conductorRepository.findById(request.getIdConductor())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Conductor No Existe."));

            if (request.getCalificacion() != null) {
                if (request.getNumeroIdentificacionUsuario() != null) {
                    if (peticionarioEntity == null) {
                        peticionarioEntity = savePeticionarioEvaluador(request);
                    }

                    if (request.getCalificacion() != null) {
                        Optional<CalificacionesEntity> calificacion = calificacionesRepository.findByPeticionarioOrderByFechaModificacionDesc
                                (peticionarioEntity).stream().findFirst();
                        if (calificacion.isPresent()) {
                            if (!validateHoraCalificacion(calificacion.get())) {
                                response.setMensaje("Ya realizó una calificación en las ultimas 12 horas a éste conductor.");
                                return response;
                            }
                        }
                    }
                    saveCalificacion(request, conductor, peticionarioEntity);
                    response.setMensaje("Calificación Guardada con Éxito");
                    return response;
                }
                saveCalificacion(request, conductor, peticionarioEntity);
                response.setMensaje("Calificación Guardada con Éxito");
            }

            if (request.getPqr() != null) {
                ParametroSimurEntity orfeoConsultaRadicado = parametroSimurRepository.findByCodigoParametro(URLConsultaRadicadoOrfeo);
                ParametroSimurEntity orfeoUrl = parametroSimurRepository.findByCodigoParametro(URLOrfeo);

                savePqr(request, conductor, peticionarioEntity);
                OrfeoRequest request1 = createRequestOrfeo(request);
                OrfeoResponse orfeoResponse = new OrfeoResponse();
                try {
                    orfeoResponse = orfeoClient.createRadicado(new URI(orfeoUrl.getValorParametro()), request1);
                } catch (FeignException.FeignClientException feignException) {
                    response.setError(feignException.getMessage());
                }
                assert orfeoResponse != null;
                response.setMensaje("Radicado con éxito, puedes visualizarlo en: " + orfeoConsultaRadicado.getValorParametro());
                response.setRadicado(orfeoResponse.getDescripcion());
            }
            return response;
        } catch (Exception e) {
            return PqrResponseDto.builder().mensaje(e.getMessage()).build();
        }
    }

    public Boolean validateHoraCalificacion( CalificacionesEntity entity ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entity.getFechaModificacion());
        calendar.add(Calendar.HOUR, 12);
        Date dateCalification = calendar.getTime();
        Date currentDate = new Date();
        return dateCalification.before(currentDate);
    }

    @Transactional
    public void saveCalificacion( PqrRequestDTO request, ConductorEntity conductor, PeticionarioEntity peticionario ) {
        CalificacionesEntity calificacionesEntity = calificacionesMapper.toEntityFromRequest(request.getCalificacion());
        calificacionesEntity.setConductor(conductor);
        calificacionesEntity.setPlacaVehiculo(request.getPlacaVehiculo());
        calificacionesEntity.setIdCache(request.getIdCache());
        calificacionesEntity.setFechaModificacion(new Date());
        calificacionesEntity.setPeticionario(peticionario.getNumeroIdentificacionUsuario() == null ? null : peticionario);
        calificacionesEntity.setAutomatico(request.getAutomatico());
        calificacionesRepository.save(calificacionesEntity);
    }

    @Transactional
    public void saveCalificacionEmpty( PqrRequestDTO request ) {
        CalificacionesEntity calificacionesEntity = new CalificacionesEntity();
        calificacionesEntity.setPlacaVehiculo(request.getPlacaVehiculo());
        calificacionesEntity.setAutomatico(request.getAutomatico());
        calificacionesRepository.save(calificacionesEntity);
    }

    @Transactional
    public void savePqr( PqrRequestDTO request, ConductorEntity conductor, PeticionarioEntity peticionario ) {
        PqrsEntity pqrsEntity = pqrMapper.toEntityFromRequest(request.getPqr());
        pqrsEntity.setConductor(conductor);
        pqrsEntity.setPlacaVehiculo(request.getPlacaVehiculo());
        pqrsEntity.setIdCache(request.getIdCache());
        pqrsEntity.setPeticionario(peticionario);
        pqrRepository.save(pqrsEntity);
    }

    @Transactional
    public PeticionarioEntity savePeticionarioEvaluador( PqrRequestDTO request ) {
        PeticionarioEntity peticionarioEntity = new PeticionarioEntity();
        peticionarioEntity.setTipoPeticionario(request.getTipoPeticionario());
        peticionarioEntity.setTipoIdentificacionUsuario(request.getTipoIdentificacionUsuario());
        peticionarioEntity.setNumeroIdentificacionUsuario(request.getNumeroIdentificacionUsuario());
        peticionarioEntity.setGenero(request.getGenero());
        peticionarioEntity.setNombres(request.getNombres());
        peticionarioEntity.setPrimerApellido(request.getPrimerApellido());
        peticionarioEntity.setSegundoApellido(request.getSegundoApellido());
        peticionarioEntity.setMunicipio(request.getMunicipio());
        peticionarioEntity.setDireccion(request.getDireccion());
        peticionarioEntity.setCorreoElectronico(request.getCorreoElectronico());
        peticionarioEntity.setTelefonoCelular(request.getTelefonoCelular());
        return peticionarioRepository.save(peticionarioEntity);
    }

    private OrfeoRequest createRequestOrfeo( PqrRequestDTO request ) {
        OrfeoRequest orfeoRequest = new OrfeoRequest();
        orfeoRequest.setUsuarioApp(VariablesOrfeo.usuarioApp.getValue());
        orfeoRequest.setContrasenaApp(VariablesOrfeo.contrasenaApp.getValue());
        orfeoRequest.setTipo(2);
        orfeoRequest.setAsunto(request.getPqr().getAsunto());
        orfeoRequest.setUsuaRadica(VariablesOrfeo.usuaRadica.getValue());
        orfeoRequest.setTipoTercero(1);
        orfeoRequest.setNombreTercero(request.getNombres());
        orfeoRequest.setPrimerApellidoTercero(request.getPrimerApellido());
        orfeoRequest.setSegundoApellidoTercero(request.getSegundoApellido());
        orfeoRequest.setIdentificacionTercero(request.getNumeroIdentificacionUsuario());
        orfeoRequest.setEmailTercero(request.getCorreoElectronico());
        orfeoRequest.setDireccionTercero(request.getDireccion());
        orfeoRequest.setContinente(1);
        orfeoRequest.setPais(170);
        orfeoRequest.setDepartamento(11);
        orfeoRequest.setMunicipio(1);
        orfeoRequest.setMedioRecepcion(3);
        orfeoRequest.setFechaOficioRadicado("");
        orfeoRequest.setDignatario("");
        orfeoRequest.setLoginFirma(VariablesOrfeo.loginFirma.getValue());
        orfeoRequest.setRadicadoAsociado("");
        orfeoRequest.setCodigoPostal("");
        orfeoRequest.setTieneAnexos(0);
        orfeoRequest.setDescripcionAnexos("");
        orfeoRequest.setCriterio(0);
        orfeoRequest.setCodigoBarrioLocalNotif(0);
        orfeoRequest.setCodigoBarrioLocal(0);
        orfeoRequest.setCuentaInterna(VariablesOrfeo.cuentaInterna.getValue());
        orfeoRequest.setDepActual(421);
        orfeoRequest.setVigencia(0);
        orfeoRequest.setUsuarioinforma("");
        orfeoRequest.setNumeroComparendo(0);
        orfeoRequest.setNumeroContrato("");
        orfeoRequest.setTipoTercerociu(1);
        orfeoRequest.setPrimerApellidoTercerociu("");
        orfeoRequest.setSegundoApellidoTercerociu("");
        orfeoRequest.setIdentificacionTercerociu("");
        orfeoRequest.setEmailTercerociu("");
        orfeoRequest.setTelefonoTercerociu("");
        orfeoRequest.setDireccionTercerociu("");
        orfeoRequest.setContinenteciu(1);
        orfeoRequest.setPaisciu(170);
        orfeoRequest.setDepartamentociu(11);
        orfeoRequest.setMunicipiociu(1);
        orfeoRequest.setMedioRecepcionciu(3);
        orfeoRequest.setFechaOficioRadicadociu("");
        orfeoRequest.setDignatariociu("");
        orfeoRequest.setCodigoBarrioLocalNotifciu("");
        return orfeoRequest;
    }
}
