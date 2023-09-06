package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_REFRENDACION_HISTORICO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefrendacionHistoricoEntity {

    @Id
    @Column(name = "ID_REFRENDACION_HISTORICO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REFRENDACION_HISTORICO")
    @SequenceGenerator(name = "SEQ_REFRENDACION_HISTORICO", sequenceName = "SEQ_REFRENDACION_HISTORICO", allocationSize = 1)
    private long idRefrendacionHistorico;

    @Column(name = "FECHA_REFRENDACION")
    private Date fechaRefrendacion;

    @Column(name = "ID_VEHICULO")
    private long idVehiculo;

}
