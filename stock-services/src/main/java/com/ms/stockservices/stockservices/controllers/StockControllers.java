package com.ms.stockservices.stockservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/api/stockservices")
public class StockControllers {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") final String username){
        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange
                ("http://db-service/rest/api/dbservices/" + username,
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<String>>() {});
        List<String> quotes = quoteResponse.getBody();
               return quotes
                       .stream()
                       //.map(this::getStockByQuote)
                       .map(quote->{
                           try {
                               System.out.println("OOOOOOOOOOO"+quote);
                               Stock stock = getStockByQuote(quote);
                               return new Quote(quote, stock.getQuote().getPrice());
                           } catch (Exception e) {
                               e.printStackTrace();
                               return new Quote(quote, new BigDecimal(0));
                           }
                       })
                       .collect(Collectors.toList());

    }

    private Stock getStockByQuote(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }

    private class Quote {
        private String quote;
        private BigDecimal price;

        public Quote(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
