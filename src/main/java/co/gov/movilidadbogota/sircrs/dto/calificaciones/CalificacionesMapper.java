package co.gov.movilidadbogota.sircrs.dto.calificaciones;

import co.gov.movilidadbogota.sircrs.model.CalificacionesEntity;
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
public interface CalificacionesMapper {

    CalificacionesEntity toEntityFromRequest( CalificacionesDTO dto  );
}
