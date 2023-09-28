package co.gov.movilidadbogota.sircrs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_PETICIONARIO_EVALUADOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeticionarioEntity {
    @Id
    @Column(name = "ID_PETICIONARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PETICIONARIO")
    @SequenceGenerator(name = "SQ_PETICIONARIO", sequenceName = "SQ_PETICIONARIO", allocationSize = 1)
    private long id;

    @Column(name = "TIPO_PETICIONARIO")
    private Integer tipoPeticionario;

    @Column(name = "TIPO_IDENTIFICACION")
    private Integer tipoIdentificacionUsuario;

    @Column(name = "NUMERO_IDENTIFICACION")
    private Integer numeroIdentificacionUsuario;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;

    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;

    @Column(name = "MUNICIPIO")
    private String municipio;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;

    @Column(name = "TELEFONO")
    private String telefonoCelular;
}
