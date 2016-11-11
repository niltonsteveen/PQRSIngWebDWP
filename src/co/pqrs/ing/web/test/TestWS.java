package co.pqrs.ing.web.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestWS {

	//@Test
	public void testPost() {

		Client client = Client.create();
		
		WebResource webResource = client.resource("http://localhost:8080/PQRSIngWebDWP/rest/Encuesta/crearPlantilla").queryParam("plantilla", "{\"tipo\":\"PETICION\",\"nombre\":\"Plantilla Numero 1\"}").queryParam("user", "camilo").queryParam("pass", "12345");
		//String input="&user=camilo&pass=12345";
		ClientResponse response= webResource.type("application/json").post(ClientResponse.class);
		if(response.getStatus()== 201){
			System.out.println(response.getStatus());
			assertTrue(true);
		}
	
		System.out.println(response.getEntity(String.class));
		
	}
	
	//@Test
	public void testGet() {

		Client client = Client.create();
		
		WebResource webResource = client.resource("http://localhost:8080/PQRSIngWebDWP/rest/Encuesta/listarPlantillas").queryParam("user", "camilo").queryParam("pass", "12345");
		//String input="&user=camilo&pass=12345";
		ClientResponse response= webResource.accept("application/json").get(ClientResponse.class);
		if(response.getStatus() == 200){
			System.out.println(response.getStatus());
			assertTrue(true);
		}
	
		System.out.println(response.getEntity(String.class));
		
	}
	
	
	 	@Test
		public void testPut() {

			Client client = Client.create();
			
			WebResource webResource = client.resource("http://localhost:8080/PQRSIngWebDWP/rest/Encuesta/modificarPlantilla").queryParam("plantilla", "{\"codigo\":\"1\",\"tipo\":\"PETICION\",\"nombre\":\"Plantilla Numero 1 mod\"}").queryParam("user", "camilo").queryParam("pass", "12345");
			//String input="&user=camilo&pass=12345";
			ClientResponse response= webResource.type("application/json").put(ClientResponse.class);
			if(response.getStatus()== 201){
				System.out.println(response.getStatus());
				assertTrue(true);
			}
		
			System.out.println(response.getEntity(String.class));
			
		}

}
