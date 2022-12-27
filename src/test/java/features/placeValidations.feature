Feature: Validate add place API

Scenario: Verify place is added successfully 
Given: Request json payload for Add place
When: User hits the POST request API call for "add place"
Then: Response status code is "200"
And: "Response status" message is "OK"
And: "scope" is "APP"
