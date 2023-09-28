package co.gov.movilidadbogota.sircrs.repository;

import co.gov.movilidadbogota.sircrs.model.PeticionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeticionarioRepository extends JpaRepository<PeticionarioEntity, Long> {

    PeticionarioEntity findByNumeroIdentificacionUsuario( Integer numeroIdentificacionUsuario );
}
