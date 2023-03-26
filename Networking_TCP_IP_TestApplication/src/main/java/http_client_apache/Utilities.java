/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Utilities.java class
*
* @name    : Utilities.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 22, 2021
****************************************************************************/

package http_client_apache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public abstract class Utilities 
{
	protected static void sleep(int milliSeconds) {
    	try {
			TimeUnit.MILLISECONDS.sleep(milliSeconds);
		} catch (InterruptedException e) {
			// TODO
		}
    }
	
	protected static void logResponse(CloseableHttpResponse response) throws ParseException, IOException {
		System.out.println(response.getStatusLine().toString()); 
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            System.out.println(result);
        }
	}
    
	protected static String convertResponseToString(HttpResponse response) {
		try {
			InputStream responseStream = response.getEntity().getContent();
			Scanner scanner = new Scanner(responseStream, StandardCharsets.UTF_8);
			String responseString = scanner.useDelimiter("\\Z").next();
			scanner.close();
			return responseString;
		}  catch (final UnsupportedOperationException | IOException exc) {
			System.err.println(exc.getMessage());
		}
		return "";
	}

	protected static String firstLineOfResponse(HttpResponse httpResponse) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()))) {
            return reader.readLine();
        }
    }
}
