package sigblockchain.projectred.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class AccountTest {

	@Test
	public void testAccountTest1() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("1323", 10);
		var nonce = new BigInteger("4324", 10);
		;
		try {
			Account account = new Account(addr, balance, nonce);
		} catch (Exception e) {
			fail("An exception occurred for a valid construction of account object: " + e.getMessage());
		}
	}

	@Test
	public void testAccountTest2() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("8fffffffffffffff", 16); //bigger than long.MAX_VALUE
		var nonce = new BigInteger("8fffffffffffffff", 16); //bigger than long.MAX_VALUE
		try {
			Account account = new Account(addr, balance, nonce);
		} catch (Exception e) {
			fail("An exception occurred for a valid construction of account object: " + e.getMessage());
		}
	}

	@Test
	public void testAccountTest3() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("-2", 16);
		var nonce = new BigInteger("-2", 16);
		try {
			Account account = new Account(addr, balance, nonce);
			fail("Account object was allowed to be made with invalid arguments");
		} catch (Exception ignored) {
		}
	}

	@Test
	public void testAccountGetters() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("1323", 10);
		var nonce = new BigInteger("4324", 10);

		var account = new Account(addr, balance, nonce);
		assertEquals(account.getWalletAddress(), addr);
		assertEquals(account.getBalance(), balance);
		assertEquals(account.getStateNonce(), nonce);
	}

	@Test
	public void testAccountSetters1() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("1323", 10);
		var nonce = new BigInteger("4324", 10);
		var account = new Account(addr, balance, nonce);

		var newBalance = new BigInteger("64534", 10);
		var newNonce = new BigInteger("4", 10);

		account.setBalance(newBalance);
		account.setStateNonce(newNonce);

		assertEquals(account.getBalance(), newBalance);
		assertEquals(account.getStateNonce(), newNonce);
	}

	@Test
	public void testAccountSetters2() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("1323", 10);
		var nonce = new BigInteger("4324", 10);
		var account = new Account(addr, balance, nonce);

		var newBalance = new BigInteger("8fffffffffffffff", 16);
		var newNonce = new BigInteger("8fffffffffffffff", 16);

		account.setBalance(newBalance);
		account.setStateNonce(newNonce);

		assertEquals(account.getBalance(), newBalance);
		assertEquals(account.getStateNonce(), newNonce);
	}

	@Test
	public void testAccountSetters3() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("1323", 10);
		var nonce = new BigInteger("4324", 10);
		var account = new Account(addr, balance, nonce);

		var newBalance = new BigInteger("-3", 16);
		var newNonce = new BigInteger("-4", 16);


		try {
			account.setBalance(newBalance);
			fail("Allowed to set balance to an invalid value");
		} catch (Exception ignored) {
			assertEquals(account.getBalance(), balance);
		}

		try {
			account.setStateNonce(newNonce);
			fail("Allowed to set state nonce to an invalid value");
		} catch (Exception ignored) {
			assertEquals(account.getStateNonce(), nonce);
		}
	}

	@Test
	public void testAccountSetters4() {
		var addr = "81aaf16d7e4b626dc1c34c47bf9973496a3698a6e7ab0255af867169b43529fb";
		var balance = new BigInteger("1323", 10);
		var nonce = new BigInteger("4324", 10);
		var account = new Account(addr, balance, nonce);

		var newBalance = new BigInteger("100000000000000000", 16);
		var newNonce = new BigInteger("100000000000000000", 16);


		try {
			account.setBalance(newBalance);
			fail("Allowed to set balance to an invalid value");
		} catch (Exception ignored) {
			assertEquals(account.getBalance(), balance);
		}

		try {
			account.setStateNonce(newNonce);
			fail("Allowed to set state nonce to an invalid value");
		} catch (Exception ignored) {
			assertEquals(account.getStateNonce(), nonce);
		}
	}

}
