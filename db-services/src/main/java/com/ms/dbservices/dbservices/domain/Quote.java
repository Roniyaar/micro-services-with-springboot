package com.ms.dbservices.dbservices.domain;

import javax.persistence.*;
import java.util.List;

@Table(name = "quote")
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(name = "username")
    public String username;

    @Column(name = "quote")
    public String quote;

    public Quote() {
    }

    public Quote(String username, String quote) {
        this.username = username;
        this.quote = quote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
