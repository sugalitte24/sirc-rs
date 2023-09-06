package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
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

    @Column(name = "ID_VEHICULO")
    private Long idVehiculo;

    @Column(name = "ID_TIPO_DOCUMENTO")
    private Integer idTipoIdentificacion;

    @Column(name = "NUMERO_DOCUMENTO")
    private Integer numeroDocumento;

    @Column(name = "ID_CACHE")
    private String idCache;

    @Column(name = "VALORACION")
    private String valoracion;

    @Column(name = "BUEN_TRABAJO")
    private String buenTrabajo;

    @Column(name = "CONDUCCION_SEGURA")
    private String conduccionSegura;

    @Column(name = "SIGUIO_RUTA")
    private String siguioRuta;

    @Column(name = "VEHICULO_BUENO")
    private String vehiculoBueno;

    @Column(name = "MAL_SERVICIO")
    private String malServicio;

    @Column(name = "COBRO_INADECUADO")
    private String cobroInadecuado;

    @Column(name = "NO_SIGUE_RUTA")
    private String noSigueRuta;

    @Column(name = "MAL_VEHICULO")
    private String malVehiculo;

    @Column(name = "TRATO_CONDUCTOR")
    private String tratoConductor;

    @Column(name = "ACOSO_DISCRIMINACION")
    private String acosoDiscriminacion;

    @Column(name = "PRESTACION_INCOMPLETA")
    private String prestacionIncompleta;

    @Column(name = "MANEJO_AGRESIVO")
    private String manejoAgresivo;

    @Column(name = "INFORMACION_IRREGULAR")
    private String informacionIrregular;

    @Column(name = "OTRO")
    private String otro;

    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
}
