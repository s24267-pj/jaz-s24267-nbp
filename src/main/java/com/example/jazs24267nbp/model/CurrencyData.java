package com.example.jazs24267nbp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table()
public class CurrencyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unikalne id.")
    private Long id;
    @Schema(description = "Kod waluty.")
    private String currency;
    @Schema(description = "Wyliczona srednia.")
    private double average;
    @Schema(description = "Data oraz godzina wyslania zapytania")
    private LocalDateTime request_datetime;
    @Schema(description = "Data rozpoczynajaca przedzial dla liczenia kursu sredniego.")
    private String start_date;
    @Schema(description = "Data konczaca przedzial dla liczenia kursu sredniego.")
    private String end_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public LocalDateTime getRequest_datetime() {
        return request_datetime;
    }

    public void setRequest_datetime(LocalDateTime request_datetime) {
        this.request_datetime = request_datetime;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
