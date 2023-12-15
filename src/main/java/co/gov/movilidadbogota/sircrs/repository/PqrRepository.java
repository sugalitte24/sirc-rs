package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.PeticionarioEntity;
import co.gov.movilidadbogota.sircrs.model.PqrsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PqrRepository extends JpaRepository<PqrsEntity, Long> {

    List<PqrsEntity> findByPeticionarioAndPlacaVehiculoOrderByFechaRadicadoDesc( PeticionarioEntity peticionario, String placa );

}
