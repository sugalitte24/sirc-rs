package co.gov.movilidadbogota.sircrs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_TIPO_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioEntity {

    @Id
    @Column(name = "ID_TIPO_USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_usuario")
    @SequenceGenerator(name = "sq_tipo_usuario", sequenceName = "sq_tipo_usuario", allocationSize = 1)
    private long id;

    @Column(name = "DESCRIPCION_TIPO")
    private String descripcionTipo;
}
