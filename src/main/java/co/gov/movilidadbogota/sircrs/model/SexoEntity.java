package co.gov.movilidadbogota.sircrs.model;

import co.gov.movilidadbogota.sircrs.model.enums.SexoEnum;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SMI_SEXO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SexoEntity {
    @Id
    @Column(name = "ID_SEXO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_sexo")
    @SequenceGenerator(name = "sq_sexo", sequenceName = "sq_sexo", allocationSize = 1)
    private long id;

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
}
