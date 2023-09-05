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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PersonaRepository personaRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private OrfeoClient orfeoClient;

    @Autowired
    private CalificacionesMapper calificacionesMapper;

    @Autowired
    private PqrMapper pqrMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public PqrResponseDto createPqr( PqrRequestDTO request ) {
        try {
            ConductorEntity conductor = conductorRepository.findById(request.getIdConductor()).get();
            PqrResponseDto response = new PqrResponseDto();
            if (request.getCalificacion() != null) {
                Optional<CalificacionesEntity> calificacion = calificacionesRepository.findByNumeroIdentificacionUsuario
                        (Long.valueOf(request.getNumeroIdentificacionUsuario()));
                if (calificacion.isPresent()) {
                    if (validateHoraCalificacion(calificacion.get())) {
                        response.setMensaje("Ya realizó una calificación en las ultimas 12 horas a éste conductor.");
                        return response;
                    }
                }
                saveCalificacion(request, conductor);
                response.setMensaje("Calificación Guardad con Éxito");
            }

            if (request.getPqr() != null) {
                TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findById(Long.valueOf(request.getTipoIdentificacionUsuario())).get();
                PersonaEntity persona = personaRepository.findByNumeroDocumentoAndTipoDocumento(
                        Long.valueOf(request.getNumeroIdentificacionUsuario()), tipoDocumento);

                savePqr(request, conductor);
                OrfeoRequest request1 = createRequestOrfeo(request, persona);
                //System.out.println(objectMapper.writer().writeValueAsString(request1));
                OrfeoResponse orfeoResponse = orfeoClient.createRadicado(request1);
                assert orfeoResponse != null;
                response.setMensaje("Radicado con éxito");
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
    public void saveCalificacion( PqrRequestDTO request, ConductorEntity conductor ) {
        CalificacionesEntity calificacionesEntity = calificacionesMapper.toEntityFromRequest(request.getCalificacion());
        calificacionesEntity.setConductor(conductor);
        calificacionesRepository.save(calificacionesEntity);
    }

    @Transactional
    public void savePqr( PqrRequestDTO request, ConductorEntity conductor ) {
        PqrsEntity pqrsEntity = pqrMapper.toEntityFromRequest(request.getPqr());
        pqrsEntity.setConductor(conductor);
        pqrRepository.save(pqrsEntity);
    }

    private OrfeoRequest createRequestOrfeo( PqrRequestDTO request, PersonaEntity persona ) {
        OrfeoRequest orfeoRequest = new OrfeoRequest();
        orfeoRequest.setUsuarioApp(VariablesOrfeo.usuarioApp.getValue());
        orfeoRequest.setContrasenaApp(VariablesOrfeo.contrasenaApp.getValue());
        orfeoRequest.setTipo(2);
        orfeoRequest.setAsunto(request.getPqr().getAsunto());
        orfeoRequest.setUsuaRadica(VariablesOrfeo.usuaRadica.getValue());
        orfeoRequest.setTipoTercero(1);
        orfeoRequest.setNombreTercero(persona.getNombres());
        orfeoRequest.setPrimerApellidoTercero(persona.getApellidos());
        orfeoRequest.setSegundoApellidoTercero("");
        orfeoRequest.setIdentificacionTercero(request.getNumeroIdentificacionUsuario());
        orfeoRequest.setEmailTercero(persona.getCorreoElectronico());
        orfeoRequest.setTelefonoTercero(request.getTelefonoFijo());
        orfeoRequest.setDireccionTercero(persona.getDireccion());
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
