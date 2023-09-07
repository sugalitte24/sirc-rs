package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.CalificacionesEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionesRepository extends JpaRepository<CalificacionesEntity, Long> {

    List<CalificacionesEntity> findByNumeroDocumentoOrderByFechaModificacionDesc( Integer numeroDocumento );
}
