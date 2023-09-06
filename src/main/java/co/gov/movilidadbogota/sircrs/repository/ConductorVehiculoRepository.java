package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.ConductorVehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorVehiculoRepository extends JpaRepository<ConductorVehiculoEntity, Long> {

    ConductorVehiculoEntity findByTarjetaControl(String tarjetaControl);
}
