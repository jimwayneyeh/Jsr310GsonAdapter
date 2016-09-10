package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestLocalDateEpochAdapter {
  private JsonParser parser = new JsonParser();
  private Gson gson = new GsonBuilder()
      .registerTypeAdapter(LocalDate.class, new LocalDateEpochAdapter())
      .create();
  
  @Test
  public void testSerialization () {
    MyObject myObj = new MyObject();
    
    Assert.assertEquals(
        LocalDate.MIN.toEpochDay(), 
        this.parser.parse(this.gson.toJson(myObj.setDate(LocalDate.MIN)))
            .getAsJsonObject()
            .get("date").getAsLong());
    Assert.assertEquals(
        LocalDate.MAX.toEpochDay(), 
        this.parser.parse(this.gson.toJson(myObj.setDate(LocalDate.MAX)))
            .getAsJsonObject()
            .get("date").getAsLong());
    Assert.assertEquals(
        LocalDate.now().toEpochDay(), 
        this.parser.parse(this.gson.toJson(myObj.setDate(LocalDate.now())))
            .getAsJsonObject()
            .get("date").getAsLong());
  }
  
  @Test
  public void testDeserialization () {
    MyObject myObj = null;
    JsonObject json = new JsonObject();
    
    json.addProperty("date", LocalDate.MIN.toEpochDay());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        LocalDate.MIN.toEpochDay(), 
        myObj.getDate().toEpochDay());
    
    json.addProperty("date", LocalDate.MAX.toEpochDay());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        LocalDate.MAX.toEpochDay(), 
        myObj.getDate().toEpochDay());
    
    json.addProperty("date", LocalDate.now().toEpochDay());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        LocalDate.now().toEpochDay(), 
        myObj.getDate().toEpochDay());
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
