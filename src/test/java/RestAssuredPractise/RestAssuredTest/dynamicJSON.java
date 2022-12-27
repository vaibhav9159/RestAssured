package RestAssuredPractise.RestAssuredTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonPayload.jsonPayloads;
import reusableUtilities.reusableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class dynamicJSON {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().body(jsonPayloads.addBook(isbn, aisle)).header("Content-Type", "application/json")
			.when().post("Library/Addbook.php")
			.then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();
		
		JsonPath js = reusableMethods.rawToJson(response);
		String ID = js.getString("ID");
		
	
		//delBooks
		given().body(jsonPayloads.deleteBook(ID)).when().post("/Library/DeleteBook.php").then().assertThat().statusCode(200);  
		
		System.out.println("Both books added and deleted successfully");	
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		
		return new Object [][] {{"aumNamahShivaya13","1283"},{"aumNamahShivaya11","9765"}};
	}
	
	
}
