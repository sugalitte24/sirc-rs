package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_PQRS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PqrsEntity {
    @Id
    @Column(name = "ID_PQR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pqr")
    @SequenceGenerator(name = "sq_pqr", sequenceName = "sq_pqr", allocationSize = 1)
    private long id;

    @Column(name = "FECHA_RADICADO")
    private Date fechaRadicado = new Date();

    @Column(name = "HECHOS")
    private String hechos;

    @Column(name = "ASUNTO")
    private String asunto;

    @ManyToOne
    @JoinColumn(name = "ID_CONDUCTOR")
    private ConductorEntity conductor;

}
