package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMI_EMPRESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaEntity {

	@Id
	@Column(name = "ID_EMPRESA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_empresa")
	@SequenceGenerator(name = "sq_empresa", sequenceName = "sq_empresa", allocationSize = 1)
	private long id;

	@Column(name = "ID_NIT_EMPRESA")
	private String idNitEmpresa;

	@Column(name = "NOMBRE_RAZON_SOCIAL")
	private String nombreRazonSocial;

	@Column(name = "ID_ESTADO")
	private long idEstado;

	@Column(name = "USUARIO_MODIFICA")
	private String usuarioModifica;

	@Column(name = "FECHA_MODIFICA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModifica;

	@Column(name = "SIGLA_EMPRESA")
	private String siglaEmpresa;

	@Column(name = "CODIGO_EMPRESA", nullable = true, unique = true)
	private String codigoEmpresa;

/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	private List<ConductorVehiculoEntity> conductorVehiculoEntity;*/

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "empresaEntityList")
	private List<UsuarioEntity> usuarioEntities;

}
