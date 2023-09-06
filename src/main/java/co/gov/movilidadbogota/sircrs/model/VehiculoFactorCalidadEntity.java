package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_VEHICULO_FACTORCALIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoFactorCalidadEntity {

    @Id
    @Column(name = "ID_FACTORCALIDAD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FACTOR_CALIDAD")
    @SequenceGenerator(name = "SQ_FACTOR_CALIDAD", sequenceName = "SQ_FACTOR_CALIDAD", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private EmpresaEntity empresa;

    @Column(name = "NRO_PLACA")
    private String nroPlaca;

    @Column(name = "ID_ESTADO")
    private long idEstado;

    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    @Column(name = "FECHA_NOVEDAD")
    private Date fechaNovedad;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

}
