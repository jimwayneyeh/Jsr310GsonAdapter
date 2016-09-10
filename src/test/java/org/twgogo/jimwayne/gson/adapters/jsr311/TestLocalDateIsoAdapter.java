package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestLocalDateIsoAdapter {
  private JsonParser parser = new JsonParser();
  private Gson gson = new GsonBuilder()
      .registerTypeAdapter(LocalDate.class, new LocalDateIsoAdapter())
      .create();
  
  @Test
  public void testSerialization () {
    MyObject myObj = new MyObject();
    
    Assert.assertEquals(
        LocalDate.MIN.toString(), 
        this.parser.parse(this.gson.toJson(myObj.setDate(LocalDate.MIN)))
            .getAsJsonObject()
            .get("date").getAsString());
    Assert.assertEquals(
        LocalDate.MAX.toString(), 
        this.parser.parse(this.gson.toJson(myObj.setDate(LocalDate.MAX)))
            .getAsJsonObject()
            .get("date").getAsString());
    Assert.assertEquals(
        LocalDate.now().toString(), 
        this.parser.parse(this.gson.toJson(myObj.setDate(LocalDate.now())))
            .getAsJsonObject()
            .get("date").getAsString());
  }
  
  @Test
  public void testDeserialization () {
    MyObject myObj = null;
    JsonObject json = new JsonObject();
    
    json.addProperty("date", LocalDate.MIN.toString());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        LocalDate.MIN.toString(), 
        myObj.getDate().toString());
    
    json.addProperty("date", LocalDate.MAX.toString());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        LocalDate.MAX.toString(), 
        myObj.getDate().toString());
    
    json.addProperty("date", LocalDate.now().toString());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        LocalDate.now().toString(), 
        myObj.getDate().toString());
  }
  
  private class MyObject {
    private LocalDate date;
    
    public MyObject setDate (LocalDate date) {
      this.date = date;
      return this;
    }
    
    public LocalDate getDate () {
      return this.date;
    }
  }
}
