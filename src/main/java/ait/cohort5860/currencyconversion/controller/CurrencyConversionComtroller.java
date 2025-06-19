package ait.cohort5860.currencyconversion.controller;

import ait.cohort5860.currencyconversion.dto.ConversionResponseDto;
import ait.cohort5860.currencyconversion.dto.ConversionResponseForClientDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class CurrencyConversionComtroller {

    @GetMapping("/convert")
    public ConversionResponseForClientDto convert(@RequestParam String from, @RequestParam String to, @RequestParam String amount) {
        String data = getDataFromServer(from, to, amount);
        double resDouble = Double.parseDouble(data);
        String result = String.format("%.2f", resDouble);
        String response_string = amount + " " + from + " = " + result + " " + to;
        System.out.println(response_string);
        return new ConversionResponseForClientDto(from, to, amount, result, response_string);
    }

    public static String getDataFromServer(String from, String to, String amount) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", "zYAmX6D7SudfxOfM5DtoduaOGt80WNwI");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.apilayer.com/fixer/convert")
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("amount", amount);
        URI url = builder.build().encode().toUri();
        RequestEntity<String> reguest = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<ConversionResponseDto> response = restTemplate.exchange(reguest, ConversionResponseDto.class);
        return response.getBody() != null ? response.getBody().getResult() : null;
    }
}
