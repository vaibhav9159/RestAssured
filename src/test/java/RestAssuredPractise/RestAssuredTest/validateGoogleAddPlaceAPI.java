package RestAssuredPractise.RestAssuredTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonPayload.jsonPayloads;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class validateGoogleAddPlaceAPI {

	public static void main(String[] args) throws IOException {

		//add place 
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(jsonPayloads.addPlace())
		.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		// for parsing json , it takes string as input, to extract place id
		JsonPath j = new JsonPath(response);
		String placeID = j.getString("place_id");
		
		System.out.println("placeID is = " + placeID);
		
		//put or update the place address 
		String updatedAddress = "veer sainik jankipuram updated 226021 Lucknow ";
		given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\n"
				+ "\"place_id\":\""+placeID+"\",\n"
				+ "\"address\":\""+updatedAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("maps/api/place/update/json").then().log().all().assertThat().
				statusCode(200).body("msg", equalTo("Address successfully updated"))
				.header("Content-Type","application/json;charset=UTF-8");
		
		//get call to check the updated address
		String getUpdatedResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeID).when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = reusableUtilities.reusableMethods.rawToJson(getUpdatedResponse);
		String getUpdatedAddress = js.getString("address");
		
		Assert.assertEquals(getUpdatedAddress, updatedAddress);	
		
		
	}

}
