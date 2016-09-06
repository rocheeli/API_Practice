package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Sample class that makes simple requests to Google Maps Directions API
 * 
 * @author Elizabeth Roche
 * @since 2016-09-06
 */

public class RestRequest
{
	/**
	 * The URLof the API we want to connect to
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	/**
	 * The character set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";
	/**
	 * API key used for making requests to API
	 */
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";

	public static void main(String[] args)
	{
		try
		{
			// The origin or starting point for directions
			String origin = "Columbia MD";

			// The destination or end point for directions
			String destination = "Dewey Beach DE";

			// avoid tolls in directions
			String avoid = "tolls";

			// language of html directions (Dutch)
			String language = "nl";

			// time of departure
			String departure_time = "now";

			// anticipating bad traffic
			String traffic_model = "pessimistic";

			// changing to metric system
			String units = "metric";

			// The return type of the response
			String returnType = "json";

			// creates the URL parameters as a string encoding them with the
			// defined charset
			String queryString = String.format(
					"origin=%s&destination=%s&key=%s&avoid=%s&language=%s&departure_time=%s&traffic_model=%s&units=%s",
					URLEncoder.encode(origin, charset), URLEncoder.encode(destination, charset),
					URLEncoder.encode(key, charset), URLEncoder.encode(avoid, charset),
					URLEncoder.encode(language, charset), URLEncoder.encode(departure_time, charset),
					URLEncoder.encode(traffic_model, charset), URLEncoder.encode(units, charset)

			);

			// creates a new URL out of the endpoint, returnType, and
			// queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");

			// if we did not get 200 (success) throw an exception
			if (connection.getResponseCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code: " + connection.getResponseCode());
			}
			// read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// loop of buffer line by line until it returns null meaning no more
			// lines
			while (br.readLine() != null)
			{
				// print out each line to screen
				System.out.println(br.readLine());
			}

			// close connection to API
			connection.disconnect();

		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}