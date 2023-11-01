package co.gov.movilidadbogota.sircrs.client;


import co.gov.movilidadbogota.sircrs.dto.orfeo.OrfeoRequest;
import co.gov.movilidadbogota.sircrs.dto.orfeo.OrfeoResponse;
import feign.Headers;
import feign.RequestLine;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "orfeo")
public interface OrfeoClient {

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    OrfeoResponse createRadicado( URI orfeoUrl, OrfeoRequest request );
}
