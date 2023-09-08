package co.gov.movilidadbogota.sircrs.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_APLICACION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AplicacionEntity {

	@Id
	@Column(name = "ID_APLICACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sq_aplicacion")
	@SequenceGenerator(name = "sq_aplicacion", sequenceName = "sq_aplicacion", allocationSize=1)
	private long id;

	@Column(name = "ABREVIATURA_APLICACION")
	private String abreviaturaAplicacion;

	@Column(name = "NOMBRE_APLICACION")
	private String nombreAplicacion;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_APLICACION")
	private TipoAplicacionEntity tipoAplicacion;

	@Column(name = "ID_ESTADO")
	private long idEstado;

}
