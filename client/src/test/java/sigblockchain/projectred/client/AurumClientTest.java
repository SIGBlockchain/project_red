package sigblockchain.projectred.client;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import io.specto.hoverfly.junit5.HoverflyExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(HoverflyExtension.class)
public class AurumClientTest {

	private static String jsonFile = "account1.json";
	private static String validAddress = "91d49fb85cc6ec9b90d6af95a965a13963c80bbbfdb080876bb550766f797ff8";
	private static String validJson;
	private static String host = "aurum";

	private static HoverflyRule hoverflyRule;

	@BeforeAll
	public static void setUpJsonResponse() {
		var inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(jsonFile);
		validJson = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Test
	public void testGetAccount(Hoverfly hoverfly) {
		hoverfly.simulate(dsl(
			service(host).
				get(startsWith("/accountinfo"))
				.queryParam("w", validAddress)
				.willReturn(
					success(validJson, "application/json"))
		));

		var client = new AurumClient("aurum");
		var account = client.getAccount(validAddress);

		assertNotNull(account, "Returned a null account object");
		assertEquals(account.getWalletAddress(), validAddress);
		assertEquals(account.getStateNonce(), new BigInteger("43", 10));
		assertEquals(account.getBalance(), new BigInteger("23400000", 10));
	}


}
