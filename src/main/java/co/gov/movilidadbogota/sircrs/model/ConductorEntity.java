package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMI_CONDUCTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConductorEntity {

    @Id
    @Column(name = "ID_CONDUCTOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_conductor")
    @SequenceGenerator(name = "sq_conductor", sequenceName = "sq_conductor", allocationSize = 1)
    private long id;

    @OneToOne
    @JoinColumn(name = "ID_PERSONA")
    private PersonaEntity persona;

    @Column(name = "GRUPO_SANGUINEO")
    private String grupoSanguineo;

    @Column(name = "FACTOR_RH")
    private String factorRh;

    @Column(name = "ID_EPS")
    private long idEps;

    @Column(name = "ID_ARL")
    private long idArl;

    @Column(name = "ID_AFP")
    private long idAfp;

    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;

    @Column(name = "FECHA_MODIFICA")
    private Date fechaModifica;

    @Column(name = "RUTA_FOTO")
    private String rutaFoto;

}
