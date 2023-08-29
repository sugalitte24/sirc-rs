package co.gov.movilidadbogota.sircrs.dto.orfeo;

public enum VariablesOrfeo {
    usuarioApp("USUWEBSERVICES"),
    contrasenaApp("e10adc3949ba59abbe56e057f20f883e"),
    usuaRadica("PRURADICA"),
    loginFirma("PMALVARE1"),
    cuentaInterna("Radicacion oficio 10222"),
    ;

    private String value;
    private VariablesOrfeo( String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    };

}
