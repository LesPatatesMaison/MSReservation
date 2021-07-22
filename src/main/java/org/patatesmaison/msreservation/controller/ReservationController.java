package org.patatesmaison.msreservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservation")
@Api(value = "Reservation API", produces = "", consumes = "", tags = "Reservation", protocols = "GET, POST, PUT, DELETE")
@AllArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;


    @ApiOperation(value = "Voir une reservation", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation trouvée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "Reservation non trouvée"),
    })
    @ApiParam(name = "{id}", required = true)
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ReservationDTO get(@PathVariable("id") Long id) throws APIException {

        return reservationService.get(id);
    }


    @ApiOperation(value = "Creer une reservation", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation bien créée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReservationDTO create(@RequestBody ReservationDTO reservationDTO) throws APIException {

        return reservationService.create(reservationDTO);
    }

}
