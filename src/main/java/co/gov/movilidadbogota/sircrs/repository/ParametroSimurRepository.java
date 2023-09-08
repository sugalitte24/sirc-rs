package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.ParametroSimurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametroSimurRepository extends JpaRepository<ParametroSimurEntity, Long> {

    ParametroSimurEntity findByCodigoParametro( String codigoParametro );
}
