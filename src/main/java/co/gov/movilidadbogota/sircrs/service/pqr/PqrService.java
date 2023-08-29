package co.gov.movilidadbogota.sircrs.service.pqr;


import co.gov.movilidadbogota.sircrs.dto.pqr.PqrRequestDTO;
import co.gov.movilidadbogota.sircrs.dto.pqr.PqrResponseDto;

public interface PqrService {
    PqrResponseDto createPqr( PqrRequestDTO request );
}
