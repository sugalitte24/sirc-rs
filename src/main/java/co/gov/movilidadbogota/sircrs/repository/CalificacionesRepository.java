package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.CalificacionesEntity;
import co.gov.movilidadbogota.sircrs.model.PeticionarioEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionesRepository extends JpaRepository<CalificacionesEntity, Long> {

    List<CalificacionesEntity> findByPeticionarioAndPlacaVehiculoOrderByFechaModificacionDesc( PeticionarioEntity peticionario, String placa );
    List<CalificacionesEntity> findByIdCacheAndPlacaVehiculoOrderByFechaModificacionDesc( String idCache, String placa );
}
