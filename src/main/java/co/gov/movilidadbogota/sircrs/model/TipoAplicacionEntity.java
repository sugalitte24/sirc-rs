package co.gov.movilidadbogota.sircrs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_TIPO_APLICACION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoAplicacionEntity {

	@Id
	@Column(name = "ID_TIPO_APLICACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sq_tipo_aplicacion")
	@SequenceGenerator(name = "sq_tipo_aplicacion", sequenceName = "sq_tipo_aplicacion", allocationSize=1)
	private long id;

	@Column(name = "DESCRIPCION_TIPOAPLICACION")
	private String descripcionTipoAplicacion;

}
