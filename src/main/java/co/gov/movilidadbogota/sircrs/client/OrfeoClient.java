package co.gov.movilidadbogota.sircrs.client;


import co.gov.movilidadbogota.sircrs.dto.orfeo.OrfeoRequest;
import co.gov.movilidadbogota.sircrs.dto.orfeo.OrfeoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orfeo", url = "${rest-client.orfeo.url}")
public interface OrfeoClient {

    @PostMapping()
    OrfeoResponse createRadicado( @RequestBody OrfeoRequest request );
}
