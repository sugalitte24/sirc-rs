package co.gov.movilidadbogota.sircrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_TIPO_SERVICIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoServicioEntity {

    @Id
    @Column(name = "ID_TIPOSERVICIO")
    private long id;

    @Column(name = "DESCRIPCION_TIPOSERVICIO")
    private String descripcion;

    @Column(name = "ID_ESTADO")
    private long idEstado;

}
