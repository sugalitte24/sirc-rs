package co.gov.movilidadbogota.sircrs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_TIPO_DOCUMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumentoEntity {

    @Id
    @Column(name = "ID_TIPO_DOCUMENTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_documento")
    @SequenceGenerator(name = "sq_tipo_documento", sequenceName = "sq_tipo_documento", allocationSize = 1)
    private long id;

    @Column(name = "DESCRIPCION_TIPODOC")
    private String descripcionTipoDoc;

    @Column(name = "HOMOLOGA_DUUPS")
    private String homologaDuups;

}
