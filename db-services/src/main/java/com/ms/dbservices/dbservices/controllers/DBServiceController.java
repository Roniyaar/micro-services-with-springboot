package com.ms.dbservices.dbservices.controllers;

import com.ms.dbservices.dbservices.domain.Quote;
import com.ms.dbservices.dbservices.domain.Quotes;
import com.ms.dbservices.dbservices.repositories.QuoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/api/dbservices")
public class DBServiceController {

    private QuoteRepository quoteRepository;

    public DBServiceController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable(name = "username") final String username){

        return getQuotesByUsername(username);
    }

    private List<String> getQuotesByUsername(@PathVariable(name = "username") String username) {
        return quoteRepository.getQuotesByUsername(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody Quotes quotes){

        quotes.getQuotes().stream()
                .map(q->new Quote(quotes.getUsername(),q))
                .forEach(q->quoteRepository.save(q));
        return getQuotesByUsername(quotes.getUsername());
    }

    @DeleteMapping("/{username}")
    public List<String> delete(@PathVariable(name = "username") final String username){
        List<Quote> q = quoteRepository.getQuotesByUsername(username);
        quoteRepository.deleteAll(q);

        return getQuotesByUsername(username);
    }
}
