package co.gov.movilidadbogota.sircrs.controller;

import co.gov.movilidadbogota.sircrs.dto.pqr.PqrRequestDTO;
import co.gov.movilidadbogota.sircrs.dto.pqr.PqrResponseDto;
import co.gov.movilidadbogota.sircrs.service.pqr.PqrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sirc/api/v1/")
public class PqrsController {

    @Autowired
    private PqrService pqrService;

    @PostMapping("create-pqr")
    public PqrResponseDto createPqr( @RequestBody PqrRequestDTO request ) {
        return pqrService.createPqr(request);
    }
}
