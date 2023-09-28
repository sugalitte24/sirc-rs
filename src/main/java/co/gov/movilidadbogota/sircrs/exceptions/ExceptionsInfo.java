package co.gov.movilidadbogota.sircrs.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionsInfo {

    private int statusCode;
    private String message;
}
