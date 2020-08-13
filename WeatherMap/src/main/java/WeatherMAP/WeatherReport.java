package WeatherMAP;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class WeatherReport {
	
	@DataProvider(name="fetchdata")
	public Object[][] sendData()
	{
	 return DataInputProvider.getSheet("TC111");
	}
	
@Test(dataProvider="fetchdata")
	public  void weather(String data) {
		// TODO Auto-generated method stub
		RestAssured.baseURI ="https://api.openweathermap.org/data/2.5/weather";
		
		Response response=RestAssured
				.given()
				.param("q",data)
				.param("APPID","e2cfe5fa9e474a3f30e80b1decadd462")
				.get();
		//response code is 200
		response.then().assertThat().statusCode(200);
		//response contenttype is in JSON
		response.then().assertThat().contentType(ContentType.JSON);
		
		System.out.println(response.jsonPath().get("wind.speed"));
		System.out.println(response.jsonPath().get("main.temp_max"));
		System.out.println(response.jsonPath().get("sys.sunset"));
				
	}

}
