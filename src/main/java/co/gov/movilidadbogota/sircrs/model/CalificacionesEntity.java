package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_CALIFICACIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionesEntity {
    @Id
    @Column(name = "ID_CALIFICACION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_calificacion")
    @SequenceGenerator(name = "sq_calificacion", sequenceName = "sq_calificacion", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_CONDUCTOR")
    private ConductorEntity conductor;

    @ManyToOne
    @JoinColumn(name = "ID_PETICIONARIO")
    private PeticionarioEntity peticionario;

    @Column(name = "PLACA_VEHICULO")
    private String placaVehiculo;

    @Column(name = "ID_CACHE")
    private String idCache;

    @Column(name = "NIVEL_SATISFACCION")
    private Integer nivelSatisfaccion;

    @Column(name = "FELICITACION")
    private String felicitacion;

    @Column(name = "BUEN_TRABAJO")
    private Integer buenTrabajo;

    @Column(name = "CONDUCCION_SEGURA")
    private Integer conduccionSegura;

    @Column(name = "SIGUIO_RUTA")
    private Integer siguioRuta;

    @Column(name = "VEHICULO_BUENO")
    private Integer vehiculoBueno;

    @Column(name = "MAL_SERVICIO")
    private Integer malServicio;

    @Column(name = "COBRO_INADECUADO")
    private Integer cobroInadecuado;

    @Column(name = "NO_SIGUE_RUTA")
    private Integer noSigueRuta;

    @Column(name = "MAL_VEHICULO")
    private Integer malVehiculo;

    @Column(name = "TRATO_CONDUCTOR")
    private Integer tratoConductor;

    @Column(name = "ACOSO_DISCRIMINACION")
    private Integer acosoDiscriminacion;

    @Column(name = "PRESTACION_INCOMPLETA")
    private Integer prestacionIncompleta;

    @Column(name = "MANEJO_AGRESIVO")
    private Integer manejoAgresivo;

    @Column(name = "INFORMACION_IRREGULAR")
    private Integer informacionIrregular;

    @Column(name = "OTRO")
    private String otro;

    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;

    @Column(name = "AUTOMATICO")
    @NotNull
    private String automatico;

    @Column(name = "VISIBILIDAD_TARJETA_CONTROL")
    private String visibilidadTarjetaControl;
}
