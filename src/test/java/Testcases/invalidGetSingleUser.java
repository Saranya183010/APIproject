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

public class invalidGetSingleUser {
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
	    String endpoint = a.readexcel(9,12);
	    System.out.println(endpoint);
	  Response response = RestAssured.get(endpoint);
	  System.out.println(response.getStatusCode());
      System.out.println(response.getStatusLine());
      System.out.println(response.getTime());
      System.out.println(response.getBody().asPrettyString());
      if(response.getStatusCode()==404)
      {
          a.writexcel("testcases",12, 9, "passed");
      }
      else
      {
          a.writexcel("testcases",12, 9, "failed");
      }
  }
}
