package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMI_PERSONA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {

    @Id
    @Column(name = "ID_PERSONA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_persona")
    @SequenceGenerator(name = "sq_persona", sequenceName = "sq_persona", allocationSize = 1)
    private long id;

    @Column(name = "NUMERO_DOCUMENTO")
    private Long numeroDocumento;

    @ManyToOne
    @JoinColumn(name = "TIPO_DOCUMENTO")
    private TipoDocumentoEntity tipoDocumento;

    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @Column(name = "ID_GENERO")
    private Long idGenero;

    @Column(name = "EXTENSION")
    private Long extension;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "CELULAR")
    private Long celular;

    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;

    @Column(name = "ID_ESTADO")
    private boolean idEstado;

    @Column(name = "FECHA_EXPEDICION_DOCUMENTO")
    private Date fechaExpedicionDocumento;

    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModifica;

    @ManyToOne
    @JoinColumn(name = "ID_SEXO")
    private SexoEntity sexo;

}
