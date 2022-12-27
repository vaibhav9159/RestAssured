package jsonPayload;

public class jsonPayloads {

	
	public static String addPlace()
	{
		return "{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -26.8467,\n"
				+ "    \"lng\": 80.9462 \n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Shiv Shambhu\",\n"
				+ "  \"phone_number\": \"971191919191\",\n"
				+ "  \"address\": \"Veer amar sainik,jankipuram,Lucknow\",\n"
				+ "  \"types\": [\n"
				+ "    \"\",\n"
				+ "    \"Veer Sainik Sthal\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"Hindi\"\n"
				+ "}\n"
				+ "\n"
				+ "";
		
	}
	
	public static String courseDetailsMock()
	{
		return "{\n"
				+ "\n"
				+ "\"dashboard\": {\n"
				+ "\n"
				+ "\"purchaseAmount\": 910,\n"
				+ "\n"
				+ "\"website\": \"rahulshettyacademy.com\"\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "\"courses\": [\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Selenium Python\",\n"
				+ "\n"
				+ "\"price\": 50,\n"
				+ "\n"
				+ "\"copies\": 6\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Cypress\",\n"
				+ "\n"
				+ "\"price\": 40,\n"
				+ "\n"
				+ "\"copies\": 4\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"RPA\",\n"
				+ "\n"
				+ "\"price\": 45,\n"
				+ "\n"
				+ "\"copies\": 10\n"
				+ "\n"
				+ "}\n"
				+ "\n"
				+ "]\n"
				+ "\n"
				+ "}";
	}

	
	public static String addBook(String isbn , String aisle)
	{
		return "{\n"
		+ "\n"
		+ "\"name\":\"Learn Rest Assured Automation with Java\",\n"
		+ "\"isbn\":\""+isbn+"\",\n"
		+ "\"aisle\":\"114\",\n"
		+ "\"author\":\""+aisle+"\"\n"
		+ "}\n"
		+ " \n"
		+ "";
	}
	
	public static String deleteBook(String ID)
	{
		return "{\n"
				+ " \n"
				+ "\"ID\" : \""+ID+"\"\n"
				+ " \n"
				+ "} ";
	}
	
}
