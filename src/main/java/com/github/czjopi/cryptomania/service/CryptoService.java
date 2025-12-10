package com.github.czjopi.cryptomania.service;

import java.util.ArrayList;
import java.util.List;
// ...existing code...
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.github.czjopi.cryptomania.model.Crypto;

/**
 * Service class for managing a cryptocurrency portfolio in memory. Provides CRUD operations and
 * sorting for Crypto objects.
 */
@Service
public class CryptoService {
    private static final Logger log = LoggerFactory.getLogger(CryptoService.class);

    private final List<Crypto> cryptoPortfolio = new ArrayList<>();

    /**
     * Returns a copy of the current crypto portfolio.
     * 
     * @return list of Crypto objects
     */
    public List<Crypto> getCryptoPortfolio() {
        log.debug("Returning copy of crypto portfolio, size: {}", cryptoPortfolio.size());
        return new ArrayList<>(cryptoPortfolio);
    }

    /**
     * Adds a new Crypto to the portfolio.
     * 
     * @param crypto Crypto object to add
     * @return the added Crypto
     */
    public Crypto addCrypto(Crypto crypto) {
        cryptoPortfolio.add(crypto);
        log.info("Added crypto: {}", crypto.getName());
        return crypto;
    }

    /**
     * Finds a Crypto by its id.
     * 
     * @param id unique identifier
     * @return Crypto if found, otherwise null
     */
    public Crypto getCryptoById(int id) {
        log.debug("Searching for crypto with id: {}", id);
        return cryptoPortfolio.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    /**
     * Returns cryptos sorted by name (case-insensitive).
     * 
     * @return sorted list of Crypto objects
     */
    public List<Crypto> getCryptoSortedByName() {
        log.debug("Sorting cryptos by name");
        return cryptoPortfolio.stream()
                .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName())).toList();
    }

    /**
     * Returns cryptos sorted by price (ascending).
     * 
     * @return sorted list of Crypto objects
     */
    public List<Crypto> getCryptoSortedByPrice() {
        log.debug("Sorting cryptos by price");
        return cryptoPortfolio.stream().sorted((c1, c2) -> c1.getPrice().compareTo(c2.getPrice()))
                .toList();
    }

    /**
     * Returns cryptos sorted by quantity (ascending).
     * 
     * @return sorted list of Crypto objects
     */
    public List<Crypto> getCryptoSortedByQuantity() {
        log.debug("Sorting cryptos by quantity");
        return cryptoPortfolio.stream()
                .sorted((c1, c2) -> Double.compare(c1.getQuantity(), c2.getQuantity())).toList();
    }

    /**
     * Updates an existing Crypto in the portfolio by id.
     * 
     * @param id unique identifier of the Crypto to update
     * @param updatedCrypto Crypto object with updated values
     * @return updated Crypto if found, otherwise null
     */
    public Crypto updateCrypto(int id, Crypto updatedCrypto) {
        Crypto crypto =
                cryptoPortfolio.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (crypto != null) {
            crypto.setId(updatedCrypto.getId());
            crypto.setName(updatedCrypto.getName());
            crypto.setSymbol(updatedCrypto.getSymbol());
            crypto.setPrice(updatedCrypto.getPrice());
            crypto.setQuantity(updatedCrypto.getQuantity());
            log.info("Updated crypto: {}", crypto);
            return crypto;
        } else {
            log.warn("Crypto with id {} not found for update.", id);
            return null;
        }
    }
}
