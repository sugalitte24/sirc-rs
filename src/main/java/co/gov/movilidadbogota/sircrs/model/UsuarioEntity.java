package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMI_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

	@Id
	@Column(name = "ID_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sq_usuario")
	@SequenceGenerator(name = "sq_usuario", sequenceName = "sq_usuario", allocationSize=1)
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_PERSONA", nullable = false)
	private PersonaEntity persona;
	
	@Column(name = "LOGIN_USUARIO", nullable = false)
	private String loginUsuario;

	@Column(name = "CLAVE_USUARIO")
	private String claveUsuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERFIL")//, nullable = false
	private PerfilEntity perfil;
	
	@Column(name = "ID_ESTADO", nullable = false)
	private boolean idEstado;
	
	@Column(name = "FECHA_MODIFICA", nullable = false)
	private Date fechaModifica;
	
	@Column(name = "USR_MODIFICA") //, nullable = false
	private String usrModifica;
	
	@Column(name = "URL_SAFE_TOKEN") 
	private String urlSafeToken;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_USUARIO", nullable = false)
	private TipoUsuarioEntity idTipoUsuario;
	
	@ManyToMany
	@JoinTable(name = "SMI_EMPRESA_USUARIO", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_EMPRESA"))
	private List<EmpresaEntity> empresaEntityList;

}
