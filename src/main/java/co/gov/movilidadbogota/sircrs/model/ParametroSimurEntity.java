package co.gov.movilidadbogota.sircrs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_PARAMETRO_SIMUR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroSimurEntity {

	@Id
	@Column(name = "ID_PARAMETRO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sq_parametro")
	@SequenceGenerator(name = "sq_parametro", sequenceName = "sq_parametro", allocationSize=1)
	private long id;

	@ManyToOne
	@JoinColumn(name = "ID_APLICACION")
	private AplicacionEntity aplicacion;

	@Column(name = "CODIGO_PARAMETRO")
	private String codigoParametro;

	@Column(name = "VALOR_PARAMETRO")
	private String valorParametro;

	@Column(name = "DESCRIPCION_PARAMETRO")
	private String descripcionParametro;

	@Column(name = "ID_ESTADO")
	private boolean estado;

	@Column(name = "USR_MODIFICA")
	private String usrModifica;

	@Column(name = "FECHA_MODIFICA")
	private Date fechaModifica;

}
