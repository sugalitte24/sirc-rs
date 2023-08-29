package co.gov.movilidadbogota.sircrs.dto.pqr;

import co.gov.movilidadbogota.sircrs.model.PqrsEntity;
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
public interface PqrMapper {

    PqrsEntity toEntityFromRequest( PqrsDTO pqrsDto );
}
