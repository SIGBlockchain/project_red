package sigblockchain.projectred.client;


import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.google.gson.Gson;

public class AurumClient {

	String hostname;
	HttpClient httpclient;


	/**
	 * A client capable for engaging with Aurum's REST API.
	 *
	 * @param host The identifier for the host. Can either be hostname or IP address
	 */
	public AurumClient(String host) {
		this.hostname = host;
		this.httpclient = HttpClient.newHttpClient();
	}

	/**
	 * Gets account infomration for the /accountinfo endpoint.
	 *
	 * @param wallet the Aurum address for the account
	 * @return an instance of the Account class, containing alll the account information
	 */
	public Account getAccount(String wallet) {
		String address = "http://" + hostname + "/accountinfo?w=" + wallet;

		HttpResponse<String> response = null;
		try
		{
			response = httpclient.send(HttpRequest.newBuilder()
					.GET()
					.uri(URI.create(address))
					.setHeader("User-Agent", "Aurum Client")
					.build(), HttpResponse.BodyHandlers.ofString());
		}
		catch(Exception e)
		{System.err.println(e);}

		Gson gson = new Gson();
		return gson.fromJson(response.body(), Account.class);
	}
}
