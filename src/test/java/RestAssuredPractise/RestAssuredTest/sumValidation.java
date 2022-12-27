package RestAssuredPractise.RestAssuredTest;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import jsonPayload.jsonPayloads;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class sumValidation {

	
	@Test
	public void validateTotalSum()
	{
		
		JsonPath js = new JsonPath(jsonPayloads.courseDetailsMock());
		
		int coursesCount = js.getInt("courses.size()");
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		int sum = 0 ;
		for(int i = 0;i<coursesCount;i++)
		{
			String titles = js.getString("courses["+i+"].title");
			int coursePrices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int multiple = coursePrices*copies;
			System.out.println(titles);
			System.out.println(coursePrices);
			System.out.println(copies);
			System.out.println(multiple);
			
			sum = sum + multiple;
			System.out.println(sum + " is the toal sum of price*copies");
			
		}
		Assert.assertEquals(sum, purchaseAmt);
	}
}
