package RestAssuredPractise.RestAssuredTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {
	
	
	@Test
	public void testSerializationForGoogleMaps()
	{
		
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
		
		//send the location object as param in addPlace class object
		a.setLocation(l);
			
		//ReqSpecBuilder for generic code
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		//ResSpecBuilder for generic code
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		//adding req and res spec objects while extracting response 
		String response =	given().spec(req).body(a).when().post("maps/api/place/add/json")
		.then().spec(res).extract().response().asString();
		
		System.out.println(response);
	}

}
