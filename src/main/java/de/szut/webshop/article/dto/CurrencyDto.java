package de.szut.webshop.article.dto;

import lombok.Data;

import java.util.Map;



@Data
public class CurrencyDto {

    private Map<String, Double> conversion_rates;
}
