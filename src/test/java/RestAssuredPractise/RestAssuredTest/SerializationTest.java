package RestAssuredPractise.RestAssuredTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializationTest {
	
	
	@Test
	public void testSerializationForGoogleMaps()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setName("Shiv Shambhu");
		a.setPhone_number(919719101);
		a.setAddress("Lucknow Cantonment indian amry,Lucknow");
		a.setWebsite("www.google.co.in");
		a.setLanguage("HINDI");
		
		//to handle types array 
		List<String> typesList = new  ArrayList<String>();
		typesList.add("bharat mata ki jai ~ type 1");
		typesList.add("Vande mataram ~ type 2");
		a.setTypes(typesList);
		
		// to handle location class nested json
		location l = new location();
		l.setLat(-26.8467);
		l.setLng(80.9462);
		a.setLocation(l);
		
		String response =	given().queryParam("key", "qaclick123").body(a).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
	}

}
