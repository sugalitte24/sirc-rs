package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_PERFIL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilEntity {

    @Id
    @Column(name = "ID_PERFIL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil")
    @SequenceGenerator(name = "sq_perfil", sequenceName = "sq_perfil", allocationSize = 1)
    private long id;

    @Column(name = "DESCRIPCION_PERFIL")
    private String descripcionPerfil;

    @Column(name = "ID_ESTADO")
    private boolean idEstado;

    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;

    @Column(name = "FECHA_MODIFICA")
    private Date fechaModifica;
}
