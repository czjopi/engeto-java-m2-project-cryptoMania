package com.github.czjopi.cryptomania.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class CryptoTest {
  @Test
  void testSetNameValid() {
    Crypto crypto = new Crypto();
    crypto.setName("Ethereum");
    assertEquals("Ethereum", crypto.getName());
  }

  @Test
  void testSetNameInvalid() {
    Crypto crypto = new Crypto();
    assertThrows(IllegalArgumentException.class, () -> crypto.setName(null));
    assertThrows(IllegalArgumentException.class, () -> crypto.setName("   "));
  }

  @Test
  void testSetSymbolValid() {
    Crypto crypto = new Crypto();
    crypto.setSymbol("ETH");
    assertEquals("ETH", crypto.getSymbol());
  }

  @Test
  void testSetSymbolInvalid() {
    Crypto crypto = new Crypto();
    assertThrows(IllegalArgumentException.class, () -> crypto.setSymbol(null));
    assertThrows(IllegalArgumentException.class, () -> crypto.setSymbol("   "));
  }

  @Test
  void testEqualsAndHashCode() {
    Crypto c1 = new Crypto(1, "BTC", "BTC", new BigDecimal("1.0"), 1.0);
    Crypto c2 = new Crypto(1, "BTC", "BTC", new BigDecimal("1.0"), 1.0);
    Crypto c3 = new Crypto(2, "ETH", "ETH", new BigDecimal("2.0"), 2.0);
    assertEquals(c1, c2);
    assertEquals(c1.hashCode(), c2.hashCode());
    assertEquals(c2, c1);
    assertNotNull(c1.hashCode());
    assertFalse(c1.equals(c3));
  }

  @Test
  void testToString() {
    Crypto crypto = new Crypto(1, "Bitcoin", "BTC", new BigDecimal("50000.0"), 2.0);
    String str = crypto.toString();
    assertNotNull(str);
    assert str.contains("Bitcoin");
    assert str.contains("BTC");
  }
  @Test
  public void testCryptoDefaultConstructor() {
    Crypto crypto = new Crypto();
    assertNotNull(crypto);
  }

  @Test
  public void testCryptoParameterizedConstructor() {
    Crypto crypto = new Crypto(1, "Bitcoin", "BTC", new BigDecimal("50000.0"), 2.0);
    assertEquals(1, crypto.getId());
    assertEquals("Bitcoin", crypto.getName());
    assertEquals("BTC", crypto.getSymbol());
    assertEquals(new BigDecimal("50000.0"), crypto.getPrice());
    assertEquals(2.0, crypto.getQuantity());
  }

  @Test
  public void testSetPriceUsdToNegative() {
    Crypto crypto = new Crypto();
    BigDecimal newPrice = new BigDecimal("-60000.0");
    assertThrows(IllegalArgumentException.class, () -> crypto.setPrice(newPrice));
  }

  @Test
  public void testSetPriceUsdToValidValue() {
    Crypto crypto = new Crypto();
    BigDecimal newPrice = new BigDecimal("60000.0");
    crypto.setPrice(newPrice);
    assertEquals(newPrice, crypto.getPrice());
  }

  @Test
  public void testSetQuantity() {
    Crypto crypto = new Crypto();
    double newQuantity = 3.5;
    crypto.setQuantity(newQuantity);
    assertEquals(newQuantity, crypto.getQuantity());
  }

  @Test
  void testSetQuantityInvalid() {
    Crypto crypto = new Crypto();
    double newQuantity = -1.0;
    assertThrows(IllegalArgumentException.class, () -> crypto.setQuantity(newQuantity));
  }

  @Test
  void testSetId() {
    Crypto crypto = new Crypto();
    int newId = 10;
    crypto.setId(newId);
    assertEquals(newId, crypto.getId());
  }

  @Test
  public void testSetIdInvalid() {
    Crypto crypto = new Crypto();
    int newId = -5;
    assertThrows(IllegalArgumentException.class, () -> crypto.setId(newId));
  }

  @Test
  public void testHashCode() {
    Crypto crypto1 = new Crypto(1, "Bitcoin", "BTC", new BigDecimal("50000.0"), 2.0);
    Crypto crypto2 = new Crypto(1, "Bitcoin", "BTC", new BigDecimal("50000.0"), 2.0);
    assertEquals(crypto1.hashCode(), crypto2.hashCode());
  }
}
