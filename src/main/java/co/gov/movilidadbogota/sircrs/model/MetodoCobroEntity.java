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
@Table(name = "SMB_METODO_COBRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoCobroEntity {

    @Id
    @Column(name = "ID_METODOCOBRO")
    private long id;

    @Column(name = "DESCRIPCION_METCOBRO")
    private String descripcion;

    @Column(name = "ID_ESTADO")
    private long idEstado;

}
