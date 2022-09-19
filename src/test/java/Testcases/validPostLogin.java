package Testcases;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import utilities.excel;

public class validPostLogin {
	RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
  @Test
  public void f() throws BiffException, IOException {
	  excel n = new excel();
	  excel a=new excel();
	    a.open("C:\\Users\\Saranya J\\eclipse-workspace\\Apiproject\\src\\test\\java\\Testcases\\TestData.xls");
	    String baseURI = a.readexcel(3, 5);
	    RestAssured.baseURI = baseURI;
	    String endpoint = a.readexcel(9,18);
	    System.out.println(endpoint);
	    String email = a.readexcel(6,18);
	    System.out.println(email);
	    String password = a.readexcel(7,18);
	    System.out.println(password);
	  String jsonstring = "{\n"

            + "    \"email\": \""+ email + "\",\n"

            + "    \"password\": \""+ password + "\"\n"

            + "}";
	  RequestSpecification requestSpecification= RestAssured.given();
	  requestSpecification.contentType(ContentType.JSON);
	  requestSpecification.body(jsonstring);
	  Response response = requestSpecification.post(endpoint);
	  System.out.println(response.getStatusCode());
	  System.out.println("Creating user login successful");
	  System.out.println(response.asPrettyString());
	  System.out.println();
	  if(response.getStatusCode()==200)
      {
          a.writexcel("testcases",12, 19, "passed");
      }
      else
      {
          a.writexcel("testcases",12, 19, "failed");
      }
  }
}
