package RestAssuredPractise.RestAssuredTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import jsonPayload.jsonPayloads;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.WebAutomation;
import pojo.api;
import pojo.getCourses;
import reusableUtilities.reusableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OAuthTest {
	
	@Test
	public void oauth()
	{
/*		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.xpath("//*[@type='email' and @class='whsOnd zHQkBf']")).sendKeys("vaibhav9159@gmail.com");
		driver.findElement(By.xpath("//*[@class='dG5hZc' and @jsname='DhK0U']//*[@id='identifierNext']//*[@class='VfPpkd-vQzf8d']")).click();
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("");
		driver.findElement(By.xpath("//*[@class='qhFLie' and @jsname='k77Iif']//*[@class='VfPpkd-RLmnJb']")).click();
		String url = driver.getCurrentUrl(); */
		
		 String expectedArray[] = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qgB9xlbXlTlloHdVn5CufhDBKux7a6xI6KKV9MeUIYe9KenxKrLc_mtDVJsw_tFkg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String code = url.split("code=")[1].split("&scope")[0];
		System.out.println("code is = " + code);
		
		
		String getAccessToken= given().urlEncodingEnabled(false).queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").then().assertThat().extract().response().asString();
		
		JsonPath js = reusableUtilities.reusableMethods.rawToJson(getAccessToken);
		String accessToken = 	js.getString("access_token");
		
		 getCourses gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				 .when().get("https://rahulshettyacademy.com/getCourse.php")
				.as(getCourses.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getServices());
		System.out.println(gc.getInstructor());
		List<api> apiCoursesList = gc.getCourses().getApi();
		
		for(api apiCourse:apiCoursesList)
		{
			if(apiCourse.getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
			{
				System.out.println("Course price for " + apiCourse.getCourseTitle() + " = " + apiCourse.getPrice());
			}
		}
		
		//comparing two arrays by converting array into array list , storing all titles in an empty array list
		ArrayList actuaList = new ArrayList();
		List<WebAutomation> webAutomationList = gc.getCourses().getWebAutomation();
		for(WebAutomation webCourses:webAutomationList)
		{
			System.out.println("All course titles extracted for webAutomation are " + webCourses.getCourseTitle());
			actuaList.add(webCourses.getCourseTitle());
		}
		
		List <String> ExpectedList = Arrays.asList(expectedArray);
		
		Assert.assertEquals(ExpectedList, actuaList);
		
		
	}

}
