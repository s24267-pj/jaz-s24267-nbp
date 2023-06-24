package com.example.jazs24267nbp.service;

import com.example.jazs24267nbp.model.CurrencyData;
import com.example.jazs24267nbp.model.NbpResponseData;
import com.example.jazs24267nbp.repository.CurrencyDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final CurrencyDataRepository currencyDataRepository;
    private static final String apiNBPurl = "http://api.nbp.pl/api/exchangerates/rates";

    public CurrencyService(RestTemplate restTemplate, CurrencyDataRepository currencyDataRepository) {
        this.restTemplate = restTemplate;
        this.currencyDataRepository = currencyDataRepository;
    }

    public NbpResponseData getCurrencyRates(String table, String code, String start_date, String end_date) {
        String url = String.format("%s/%s/%s/%s/%s", apiNBPurl, table, code, start_date, end_date);
        return restTemplate.getForObject(url, NbpResponseData.class);
    }

    public double calculateAverageRate(List<NbpResponseData.Rate> rates) {
        return rates.stream().mapToDouble(NbpResponseData.Rate::getMid).average()
                .orElseThrow(() -> new RuntimeException("Nie można obliczyć średniego kursu"));
    }

    public CurrencyData saveCurrencyData(String currency, String start_date, String end_date, double average) {
        CurrencyData item = new CurrencyData();
        item.setCurrency(currency);
        item.setStart_date(start_date);
        item.setEnd_date(end_date);
        item.setAverage(average);
        item.setRequest_datetime(LocalDateTime.now());

        currencyDataRepository.save(item);

        return item;
    }


}