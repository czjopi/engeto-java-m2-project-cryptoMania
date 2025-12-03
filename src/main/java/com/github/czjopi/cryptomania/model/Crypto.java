package com.github.czjopi.cryptomania.model;

import java.math.BigDecimal;
import java.util.Objects;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Model class representing a cryptocurrency. Contains basic information such as id, name, symbol,
 * price and quantity.
 */
public class Crypto {
  @Min(value = 0, message = "ID must be non-negative")
  private int id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "Symbol cannot be blank")
  private String symbol;

  @NotNull(message = "Price cannot be null")
  @Min(value = 0, message = "Price must be non-negative")
  private BigDecimal price;

  @Min(value = 0, message = "Quantity must be non-negative")
  private double quantity;

  /**
   * Default constructor.
   */
  public Crypto() {}

  /**
   * Constructs a Crypto object with all fields.
   *
   * @param id unique identifier
   * @param name name of the cryptocurrency
   * @param symbol symbol of the cryptocurrency
   * @param price current price in USD
   * @param quantity amount owned
   */
  public Crypto(int id, String name, String symbol, BigDecimal price, double quantity) {
    setId(id);
    setName(name);
    setSymbol(symbol);
    setPrice(price);
    setQuantity(quantity);
  }

  /**
   * Gets the unique identifier of the cryptocurrency.
   * 
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the unique identifier.
   * 
   * @param id must be non-negative
   */
  public void setId(int id) {
    if (id < 0) {
      throw new IllegalArgumentException("ID cannot be negative");
    }
    this.id = id;
  }

  /**
   * Gets the name of the cryptocurrency.
   * 
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the cryptocurrency.
   * 
   * @param name must not be null or empty
   */
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * Gets the symbol of the cryptocurrency.
   * 
   * @return symbol
   */
  public String getSymbol() {
    if (symbol == null || symbol.trim().isEmpty()) {
      throw new IllegalArgumentException("Symbol cannot be null or empty");
    }
    return symbol;
  }

  /**
   * Sets the symbol of the cryptocurrency.
   * 
   * @param symbol symbol string, must not be null or empty
   * @throws IllegalArgumentException if symbol is null or empty
   */
  public void setSymbol(String symbol) {
    if (symbol == null || symbol.trim().isEmpty()) {
      throw new IllegalArgumentException("Symbol cannot be null or empty");
    }
    this.symbol = symbol;
  }

  /**
   * Gets the current price in USD.
   * 
   * @return price
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * Sets the current price in USD.
   * 
   * @param price must be non-negative and not null
   * @throws IllegalArgumentException if price is negative or null
   */
  public void setPrice(BigDecimal price) {
    if (price == null) {
      throw new IllegalArgumentException("Price cannot be null");
    }
    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }

    this.price = price;
  }

  /**
   * Gets the quantity owned.
   * 
   * @return quantity
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity owned.
   * 
   * @param quantity must be non-negative
   * @throws IllegalArgumentException if quantity is negative
   */
  public void setQuantity(double quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity cannot be negative");
    }
    this.quantity = quantity;
  }

  /**
   * Checks equality based on all fields.
   * 
   * @param o other object
   * @return true if all fields are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Crypto))
      return false;
    Crypto crypto = (Crypto) o;
    return id == crypto.id && Double.compare(crypto.quantity, quantity) == 0
        && Objects.equals(name, crypto.name) && Objects.equals(symbol, crypto.symbol)
        && Objects.equals(price, crypto.price);
  }

  /**
   * Generates hash code based on all fields.
   * 
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, name, symbol, price, quantity);
  }

  /**
   * Returns string representation of the Crypto object.
   * 
   * @return string representation
   */
  @Override
  public String toString() {
    return "Crypto{" + "id=" + id + ", name='" + name + '\'' + ", symbol='" + symbol + '\''
        + ", priceUsd=" + price + ", quantity=" + quantity + '}';
  }
}
