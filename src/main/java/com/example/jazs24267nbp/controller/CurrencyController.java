package com.example.jazs24267nbp.controller;

import com.example.jazs24267nbp.model.CurrencyData;
import com.example.jazs24267nbp.model.NbpResponseData;
import com.example.jazs24267nbp.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/NBP")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @Operation(summary = "Pobiera średni kurs waluty dla podanego zakresu dat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zapytanie wykonane pomyślnie", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CurrencyData.class)) }),
            @ApiResponse(responseCode = "400", description = "Niepoprawny kod waluty lub data w zlym formacie", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nie znaleziono danych dla zapytania", content = @Content),
            @ApiResponse(responseCode = "500", description = "Błąd serwera NBP lub błąd wewnętrzny", content = @Content)
    })

@GetMapping("/rate/{currency}/{start_date}/{end_date}")
public ResponseEntity<CurrencyData> getCurrencyRate(@PathVariable("currency") String currency, @PathVariable("start_date") String start_date, @PathVariable("end_date") String end_date ) {
    NbpResponseData nbpResponse = currencyService.getCurrencyRates("A", currency, start_date, end_date);
    double average = currencyService.calculateAverageRate(nbpResponse.getRates());
    CurrencyData item = currencyService.saveCurrencyData(currency, start_date,end_date, average);

    return ResponseEntity.ok(item);
}
}
