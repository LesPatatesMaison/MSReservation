package org.patatesmaison.msreservation.client;

import org.patatesmaison.msreservation.dto.BarDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConcentrateurApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${concentrateur.url.bar.search}")
    private String barSearchUlr;

    @Value("${concentrateur.url.bar.list}")
    private String barListUlr;

    @Value("${concentrateur.url.bar.id}")
    private String barIdUlr;

    public BarDTO getBarById(Long barId) throws RestClientException {
        return this.restTemplate.getForObject(String.format("%s%d", barIdUlr, barId), BarDTO.class);
    }

    public List<BarDTO> findBarByName(String barName) {
        BarDTO[] barDTOArray = this.restTemplate.getForObject(String.format("%s?%s", barSearchUlr, barName), BarDTO[].class);

        return barDTOArray == null ? new ArrayList<>() : Arrays.asList(barDTOArray);
    }

    public List<BarDTO> getBarList() {
        BarDTO[] barDTOArray = this.restTemplate.getForObject(barListUlr, BarDTO[].class);
        return barDTOArray == null ? new ArrayList<>() : Arrays.asList(barDTOArray);
    }
}
