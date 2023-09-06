package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_CONDUCTOR_VEHICULO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConductorVehiculoEntity {

    @Id
    @Column(name = "ID_VEHICULO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vehiculo")
    @SequenceGenerator(name = "sq_vehiculo", sequenceName = "sq_vehiculo", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_CONDUCTOR")
    private ConductorEntity conductor;

    @Column(name = "TARJETA_CONTROL")
    private String tarjetaControl;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private EmpresaEntity empresa;

    @Column(name = "TIPO_TRANSACCION")
    private String tipoTransaccion;

    @Column(name = "FECHA_EXPEDICION")
    private Date fechaExpedicion;

    @Column(name = "FECHA_VALIDEZ")
    private Date fechaValidez;

    @Column(name = "FECHA_VIGENCIA")
    private Date fechaVigencia;

    @Column(name = "PLACA_SERIAL_VEHICULO")
    private String placaSerialVehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private TarjetacontrolEstadoEntity idEstado;

    @Column(name = "NOTIFADO", nullable = false)
    private boolean notificated;

    @OneToOne
    @JoinColumn(name = "ID_FACTORCALIDAD")
    private VehiculoFactorCalidadEntity factorCalidad;

    @OneToOne
    @JoinColumn(name = "ID_METODOCOBRO")
    private MetodoCobroEntity metodoPago;

    @OneToOne
    @JoinColumn(name = "ID_TIPOSERVICIO")
    private TipoServicioEntity tipoServicio;

    @Column(name = "FECHA_VENCIMIENTO_SOAT")
    private Date fechaVencimientoSoat;

    @Column(name = "NRO_SOAT")
    private String nroSOAT;

    @Column(name = "FECHA_VENCIMIENTO_RTM")
    private Date fechaVencimientoRtm;

    @Column(name = "NRO_RTM")
    private String nroRTM;

    @Column(name = "NRO_TARJETA_OPERACION")
    private String nroTarjetaOperacion;

    @Column(name = "FECHA_VENCIMIENTO_TO")
    private Date fechaVencimientoTO;

    @OneToMany
    @JoinColumn(name = "ID_VEHICULO")
    private List<RefrendacionHistoricoEntity> refrendacionHistorico;

}
