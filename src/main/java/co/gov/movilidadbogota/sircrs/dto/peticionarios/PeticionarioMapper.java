package co.gov.movilidadbogota.sircrs.dto.peticionarios;

import co.gov.movilidadbogota.sircrs.dto.pqr.PqrsDTO;
import co.gov.movilidadbogota.sircrs.model.PeticionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PeticionarioMapper {

    PeticionarioEntity toEntityFromRequest( PqrsDTO pqrsDto );
}
