package RestAssuredPractise.RestAssuredTest;

import io.restassured.path.json.JsonPath;
import jsonPayload.jsonPayloads;

public class complexJSONParse {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(jsonPayloads.courseDetailsMock());
		
		//this method is only for array get get size 
		int coursesCount = js.getInt("courses.size()");
		System.out.println(js.getInt("courses.size()") + " is the total num of courses");
		js.getInt("dashboard.purchaseAmount");
		System.out.println(js.getInt("dashboard.purchaseAmount") + " is the total PurchaseAmount");
		js.getString("courses[0].title");
		System.out.println(js.getString("courses[0].title") + " is the first course title ");
		
		for(int i=0;i<coursesCount;i++)
		{
			String titles = js.getString("courses["+i+"].title");
			int coursePrices = js.getInt("courses["+i+"].price");
			System.out.println(titles);
			System.out.println(coursePrices);
			
			if(titles.contains("RPA"))
			{
				int copies = js.getInt("courses["+i+"].copies");
				System.out.println(copies+ " copies for RPA course");
				break;
			}
			
		}
		
		

	}

}
