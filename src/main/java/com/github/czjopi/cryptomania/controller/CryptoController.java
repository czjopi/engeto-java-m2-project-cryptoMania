package com.github.czjopi.cryptomania.controller;

import java.math.BigDecimal;
import java.util.List;
// ...existing code...
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.github.czjopi.cryptomania.model.Crypto;
import com.github.czjopi.cryptomania.service.CryptoService;
import jakarta.validation.Valid;

/**
 * REST controller for managing cryptocurrencies in the portfolio. Provides endpoints for CRUD
 * operations and portfolio value calculation.
 */
@RestController
public class CryptoController {

    private final CryptoService cryptoService;

    /**
     * Constructor for CryptoController.
     * 
     * @param cryptoService the service handling crypto operations
     */
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    /**
     * Retrieves all cryptocurrencies in the portfolio, optionally sorted by name, price, or
     * quantity.
     * 
     * @param sort the sorting criteria (name, price, quantity, or none)
     * @return list of Crypto objects
     */
    @GetMapping("/cryptos")
    public List<Crypto> getCryptosBy(@RequestParam(required = false) String sort) {
        if (sort == null || sort.isEmpty()) {
            sort = "none";
        }
        return switch (sort.toLowerCase()) {
            case "name" -> cryptoService.getCryptoSortedByName();
            case "price" -> cryptoService.getCryptoSortedByPrice();
            case "quantity" -> cryptoService.getCryptoSortedByQuantity();
            default -> cryptoService.getCryptoPortfolio();
        };
    }

    /**
     * Retrieves details of a specific cryptocurrency by its ID.
     * 
     * @param id the ID of the cryptocurrency
     * @return ResponseEntity containing the Crypto object or NOT_FOUND status
     */
    @GetMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> getCryptoById(@PathVariable int id) {
        Crypto crypto = cryptoService.getCryptoById(id);
        if (crypto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(crypto);
    }

    /**
     * Adds a new cryptocurrency to the portfolio.
     * 
     * @param crypto the Crypto object to add
     * @return ResponseEntity containing the added Crypto object
     */
    @PostMapping("/cryptos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Crypto> addCrypto(@Valid @RequestBody Crypto crypto) {
        Crypto added = cryptoService.addCrypto(crypto);
        return ResponseEntity.ok().body(added);
    }

    /**
     * Updates an existing cryptocurrency in the portfolio.
     * 
     * @param id the ID of the cryptocurrency to update
     * @param updatedCrypto the updated Crypto object
     * @return ResponseEntity containing the updated Crypto object or NOT_FOUND status
     */
    @PutMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> updateCrypto(@PathVariable int id,
            @Valid @RequestBody Crypto updatedCrypto) {
        Crypto existingCrypto = cryptoService.getCryptoById(id);
        if (existingCrypto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Crypto savedCrypto = cryptoService.updateCrypto(id, updatedCrypto);
        if (savedCrypto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(savedCrypto);
    }

    /**
     * Calculates the total value of the cryptocurrency portfolio.
     * 
     * @return the total portfolio value as Double
     */
    @GetMapping("/portfolio-value")
    public Double getPortfolioValue() {
        BigDecimal total = cryptoService.getCryptoPortfolio().stream()
                .map(c -> c.getPrice().multiply(BigDecimal.valueOf(c.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.doubleValue();
    }
}
