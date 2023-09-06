package co.gov.movilidadbogota.sircrs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMB_TARJETA_CONTROL_ESTADO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarjetacontrolEstadoEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sq_tarjeta_control")
	@SequenceGenerator(name = "sq_tarjeta_control", sequenceName = "sq_tarjeta_control", allocationSize=1)
	private long id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "activo")
	private boolean activo;

}
