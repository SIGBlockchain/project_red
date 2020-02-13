package sigblockchain.projectred.client;

import static org.junit.jupiter.api.Assertions.assertEquals;


import io.specto.hoverfly.junit.rule.HoverflyRule;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AurumClientTest {

	private static String jsonFile = "account1.json";
	private static String validAddress = "91d49fb85cc6ec9b90d6af95a965a13963c80bbbfdb080876bb550766f797ff8";
	private static String validJson;
	private static String host = "localhost:26000";

	private static HoverflyRule hoverflyRule;


	@BeforeAll
	static void setUpMockServer() {
		var inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(jsonFile);
		validJson = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hoverflyRule = HoverflyRule.inSimulationMode(dsl(
			service(host).
				get("/accoutinfo")
					.queryParam("w", validAddress)
				.willReturn(
					success(validJson, "application/json"))
		));
	}


	@Test
	public void testGetAccount() {
		var client = new AurumClient("localhost:26000");
		var account = client.getAccount(validAddress);

		assertNotNull(account, "Returned a null account object");
		assertEquals(account.getWalletAddress(), validAddress);
		assertEquals(account.getStateNonce(), new BigInteger("43", 10));
		assertEquals(account.getBalance(), new BigInteger("23400000", 10));
	}
}
