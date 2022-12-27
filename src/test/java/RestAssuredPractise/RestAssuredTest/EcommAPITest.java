package RestAssuredPractise.RestAssuredTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.createOrderApp;
import pojo.loginAppEcom;
import pojo.loginAppResponse;
import pojo.orders;

public class EcommAPITest {

	@Test
	public void loginApp()
	{		
		loginAppEcom l = new loginAppEcom();
		l.setUserEmail("shiva@gmail.com");
		l.setUserPassword("Shiva07#");
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		loginAppResponse loginResponse=	given().relaxedHTTPSValidation().spec(req).body(l)
				.when().post("api/ecom/auth/login").then().spec(res).extract()
				.response().as(loginAppResponse.class);
		
		String accessToken = loginResponse.getToken();
		String userID = loginResponse.getUserId();
		String message = loginResponse.getMessage();
		
		System.out.println("AccessToken extracted " + accessToken);
		
		// Create product  - sending form data using param func
		RequestSpecification createProductReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
			.addHeader("Authorization", accessToken).build();
		
		String addProductResponse = given().spec(createProductReq).param("productName", "indiaGate")
		.param("productAddedBy", userID).param("productCategory", "household")
		.param("productSubCategory", "laptops")
		.param("productPrice", "15000")
		.param("productDescription", "Addias Originals").param("productFor", "women")
		.multiPart("productImage",new File ("//users//vsrivastava//Desktop//Republic_day_IndiaGate_NayiDilli.png"))
		.when().post("api/ecom/product/add-product")
		.then().extract().response().asString();
		
	
		JsonPath js = new JsonPath(addProductResponse);
		String productID = js.get("productId");
		String addProdMessage = js.getString("message");
		System.out.println("Create Product response = " + addProductResponse);
		System.out.println("Create ProductID = " + productID);
		
		
		//Create order  - send data using list and 
		
		RequestSpecification createOrderReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", accessToken).setContentType(ContentType.JSON).build();
		
		createOrderApp co = new createOrderApp();
		co.setCountry("India");
		co.setProductOrderedId(productID);
		
		List<createOrderApp> orderList = new ArrayList<createOrderApp>();
		orderList.add(co);
		
		orders order = new orders();
		order.setOrders(orderList);
		
		String createOrderResponse = given().spec(createOrderReq).body(order).when().post("api/ecom/order/create-order").then().extract().asString();
		
		System.out.println("Create Order response = " + createOrderResponse);

	
		//delete product  - sending path param using path param func
		
		String deleteOrderResponse = given().spec(createProductReq).pathParam("productOrderID", productID)
				.when().delete("api/ecom/product/delete-product/{productOrderID}").then().extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteOrderResponse);
		String deleteMsg = js1.get("message");
		
		System.out.println("Delete Order response = " + deleteOrderResponse);
		System.out.println("Delete Order message = " + deleteMsg);
		
		
	}
	
	
	
}
