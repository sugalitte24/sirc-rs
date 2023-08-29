package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.PersonaEntity;
import co.gov.movilidadbogota.sircrs.model.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    PersonaEntity findByNumeroDocumentoAndTipoDocumento( Long numeroDocumento, TipoDocumentoEntity tipoDocumento );
}
