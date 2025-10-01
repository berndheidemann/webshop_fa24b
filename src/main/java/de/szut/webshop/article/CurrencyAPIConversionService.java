package de.szut.webshop.article;


import de.szut.webshop.article.dto.CurrencyDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CurrencyAPIConversionService {

    private RestTemplate restTemplate;

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/e73dffb0fef08352eece8052/latest/USD";

    public CurrencyAPIConversionService() {
        this.restTemplate = new RestTemplate();
    }

    public double convert(String from, String to, double amount) {
        if (to == null) {
            return amount;
        }

        var response = restTemplate.getForObject(API_URL, CurrencyDto.class);
        if (response != null && response.getConversion_rates() != null) {
            Double rateFrom = response.getConversion_rates().get(from);
            Double rateTo = response.getConversion_rates().get(to);
            if (rateFrom != null && rateTo != null) {
                return amount / rateFrom * rateTo;
            }
        }

        return amount; // No conversion if currencies are the same or unknown pair
    }


}
