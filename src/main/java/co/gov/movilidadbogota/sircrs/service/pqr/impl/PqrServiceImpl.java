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

    @Value("${pqr.url.orfeo.usuario.radica}")
    private String usuarioRadicaOrfeo;

    @Value("${pqr.url.orfeo.departamento}")
    private String departamentOrfeo;

    @Value("${pqr.url.orfeo.usuario.app}")
    private String usuarioAppOrfeo;

    @Value("${pqr.url.orfeo.contrasena.app}")
    private String constrasenaAppOrfeo;

    @Value("${mensaje.calificacion.exito}")
    private String calificacionExito;

    @Value("${mensaje.conductor.no.existe}")
    private String conductorNoExiste;

    @Value("${mensaje.calificacion.existe}")
    private String calificacionExistente;

    @Value("${mensaje.pqr.existe}")
    private String pqrExistente;

    @Value("${mensaje.pqr.calificacion.guardada}")
    private String pqrCalificacionGuardada;

    @Value("${mensaje.pqr.radicado.exito}")
    private String radicadoExitoso;

    @Value("${mensaje.pqr.radicado.fallido}")
    private String radicadoFallido;

    @Value("${parametro.automatico}")
    private String confirmaAutomatico;


    @Override
    public PqrResponseDto createPqr( PqrRequestDTO request ) {
        try {
            PqrResponseDto response = new PqrResponseDto();
            ConductorEntity conductor = null;

            if (request.getAutomatico().equals(confirmaAutomatico)) {
                saveCalificacionEmpty(request);
                response.setMensaje(calificacionExito);
                return response;
            }

            PeticionarioEntity peticionarioEntity = peticionarioRepository.findByNumeroIdentificacionUsuario(request.getNumeroIdentificacionUsuario());
            if (request.getIdConductor() != null) {
                conductor = conductorRepository.findById(request.getIdConductor()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, conductorNoExiste));
            }

            if (request.getCalificacion() != null) {
                if (request.getNumeroIdentificacionUsuario() != null) {
                    if (peticionarioEntity == null) {
                        peticionarioEntity = savePeticionarioEvaluador(request);
                    }

                    if (request.getCalificacion() != null) {
                        Optional<CalificacionesEntity> calificacion = calificacionesRepository.findByPeticionarioAndPlacaVehiculoOrderByFechaModificacionDesc(peticionarioEntity, request.getPlacaVehiculo()).stream().findFirst();
                        if (calificacion.isPresent()) {
                            if (!validateHoraCalificacion(calificacion.get().getFechaModificacion())) {
                                response.setMensaje(calificacionExistente);
                                return response;
                            }
                        }
                    }
                    saveCalificacion(request, conductor, peticionarioEntity, response);
                } else if (request.getIdCache() != null) {
                    Optional<CalificacionesEntity> calificacionCache = calificacionesRepository.findByIdCacheAndPlacaVehiculoOrderByFechaModificacionDesc(request.getIdCache(), request.getPlacaVehiculo()).stream().findFirst();

                    if (calificacionCache.isPresent()) {
                        if (!validateHoraCalificacion(calificacionCache.get().getFechaModificacion())) {
                            response.setMensaje(calificacionExistente);
                            return response;
                        }
                    }
                    saveCalificacion(request, conductor, peticionarioEntity, response);

                } else {
                    saveCalificacion(request, conductor, peticionarioEntity, response);
                }
            }

            if (request.getPqr() != null) {
                if (request.getNumeroIdentificacionUsuario() != null) {

                    Optional<PqrsEntity> pqr = pqrRepository.findByPeticionarioAndPlacaVehiculoOrderByFechaRadicadoDesc(peticionarioEntity, request.getPlacaVehiculo()).stream().findFirst();
                    if (pqr.isPresent()) {
                        if (!validateHoraCalificacion(pqr.get().getFechaRadicado())) {
                            response.setMensaje(pqrExistente);
                            return response;
                        }
                    }

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

                    if (request.getPqr() != null && request.getCalificacion() != null) {
                        response.setMensaje(pqrCalificacionGuardada + orfeoConsultaRadicado.getValorParametro());
                    } else {
                        response.setMensaje(radicadoExitoso + orfeoConsultaRadicado.getValorParametro());
                    }
                    response.setRadicado(orfeoResponse.getDescripcion());
                } else {
                    response.setError(radicadoFallido);
                }
            }

            return response;
        } catch (Exception e) {
            return PqrResponseDto.builder().mensaje(e.getMessage()).build();
        }

    }

    public Boolean validateHoraCalificacion( Date fecha ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR, 12);
        Date dateCalification = calendar.getTime();
        Date currentDate = new Date();
        return dateCalification.before(currentDate);
    }

    @Transactional
    public PqrResponseDto saveCalificacion( PqrRequestDTO request, ConductorEntity conductor, PeticionarioEntity peticionario, PqrResponseDto response ) {
        CalificacionesEntity calificacionesEntity = calificacionesMapper.toEntityFromRequest(request.getCalificacion());
        if (conductor != null) {
            calificacionesEntity.setConductor(conductor);
        }
        calificacionesEntity.setPlacaVehiculo(request.getPlacaVehiculo());
        calificacionesEntity.setIdCache(request.getIdCache());
        calificacionesEntity.setFechaModificacion(new Date());
        calificacionesEntity.setPeticionario(peticionario.getNumeroIdentificacionUsuario() == null ? null : peticionario);
        calificacionesEntity.setAutomatico(request.getAutomatico());
        calificacionesEntity.setVisibilidadTarjetaControl(request.getCalificacion().getVisibilidadTarjetaControl());
        calificacionesRepository.save(calificacionesEntity);
        response.setMensaje(calificacionExito);
        return response;

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
        if (conductor != null) {
            pqrsEntity.setConductor(conductor);
        }
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
        ParametroSimurEntity usuarioRadica = parametroSimurRepository.findByCodigoParametro(usuarioRadicaOrfeo);
        ParametroSimurEntity departamentoOrfeo = parametroSimurRepository.findByCodigoParametro(departamentOrfeo);
        ParametroSimurEntity usuarioApp = parametroSimurRepository.findByCodigoParametro(usuarioAppOrfeo);
        ParametroSimurEntity contrasenaApp = parametroSimurRepository.findByCodigoParametro(constrasenaAppOrfeo);

        OrfeoRequest orfeoRequest = new OrfeoRequest();
        orfeoRequest.setUsuarioApp(usuarioApp.getValorParametro());
        orfeoRequest.setContrasenaApp(contrasenaApp.getValorParametro());
        orfeoRequest.setTipo(2);
        orfeoRequest.setAsunto(request.getPqr().getAsunto());
        orfeoRequest.setUsuaRadica(usuarioRadica.getValorParametro());
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
        orfeoRequest.setDepActual(Integer.valueOf(departamentoOrfeo.getValorParametro()));
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
        orfeoRequest.setDescripcionPQRS(request.getPqr().getHechos());
        return orfeoRequest;
    }
}
