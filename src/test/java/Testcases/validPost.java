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

public class validPost {
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
	    String endpoint = a.readexcel(9,13);
	    System.out.println(endpoint);
	    String Name = a.readexcel(4,13);
	    System.out.println(Name);
	    String Job = a.readexcel(5,13);
	    System.out.println(Job);
      String jsonString = "{\n"
                + "    \"name\": \""+ Name +"\",\n"
                + "    \"job\": \""+ Job + "\"\n"
                + "}";
          RequestSpecification requestSpecification= RestAssured.given();
          requestSpecification.contentType(ContentType.JSON);
          requestSpecification.body(jsonString);
          Response response = requestSpecification.post(endpoint);
          String responsestring = response.asPrettyString();
          System.out.println(responsestring);
          if(response.getStatusCode()==201)
          {
              a.writexcel("testcases",12, 13, "passed");
          }
          else
          {
              a.writexcel("testcases",12, 13, "failed");
          }
  }
}