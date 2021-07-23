package org.patatesmaison.msreservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dto.AuthenticationDTO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.dto.UserDTO;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
@Api(value = "User API", produces = "", consumes = "", tags = "User", protocols = "GET, POST, PUT, DELETE")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Voir un utilisateur", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "utilisateur trouvé"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "utilisateur non trouvé"),
    })
    @ApiParam(name = "{id}", required = true)
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDTO getUser(@PathVariable("id") Long userId) throws APIException {
        return userService.getUser(userId);
    }

    @ApiOperation(value = "Voir la liste des utilisateurs", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "utilisateurs trouvés"),
    })
    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDTO> getUserList() {
        return userService.getUserList();
    }

    @ApiOperation(value = "Voir les reservations d'un utilisateur", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reservations trouvées"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "reservations non trouvées"),
    })
    @ApiParam(name = "{id}", required = true)
    @GetMapping("/{id}/reservation")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<ReservationDTO> getUserReservation(@PathVariable("id") Long userId) throws APIException {
        return userService.getUserReservation(userId);
    }

    @ApiOperation(value = "Voir les 3 reservations les plus recentes d'un utilisateur", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reservations trouvées"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "reservations non trouvées"),
    })
    @ApiParam(name = "{id}", required = true)
    @GetMapping("/{id}/reservation/recent")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<ReservationDTO> getUser3MostRecentReservations(@PathVariable("id") Long userId) throws APIException {
        return userService.getUser3MostRecentReservations(userId);
    }

    @ApiOperation(value = "Creer un utilisateur", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur bien créée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO userDTO) throws APIException {

        return userService.create(userDTO);
    }

    @ApiOperation(value = "Modifier un utilisateur", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur bien modifié"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
    })
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public UserDTO update(@RequestBody UserDTO userDTO) throws APIException {

        return userService.update(userDTO);
    }

    @ApiOperation(value = "Supprimer un utilisateur", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Utilisateur bien supprimé"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
    })
    @DeleteMapping("/{id}")
    @ApiParam(name = "{id}", required = true)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws APIException {

        userService.delete(id);
    }

    @ApiOperation(value = "Se Logger", response = ReservationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "utilisateur trouvé"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "utilisateur non trouvé"),
    })
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDTO login(@RequestBody AuthenticationDTO authenticationDTO) throws APIException {
        return userService.login(authenticationDTO);
    }

}
