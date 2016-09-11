package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestZonedDateTimeAdapter {
  private JsonParser parser = new JsonParser();
  private Gson gson = new GsonBuilder()
      .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
      .create();
  
  @Test
  public void testSerialization () {
    ZonedDateTime now = ZonedDateTime.now();
    MyObject myObj = new MyObject().setZonedDateTime(now);
    String jsonStr = this.gson.toJson(myObj);
    
    Assert.assertEquals(
        now.toString(), 
        this.parser.parse(jsonStr)
            .getAsJsonObject()
            .get("dateTime").getAsString());
  }
  
  @Test
  public void testDeserialization () {
    MyObject myObj = null;
    JsonObject json = new JsonObject();
    ZonedDateTime now = ZonedDateTime.now();
    
    json.addProperty("dateTime", now.toString());
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        now.toString(), 
        myObj.getZonedDateTime().toString());
  }
  
  private class MyObject {
    private ZonedDateTime dateTime;
    
    public MyObject setZonedDateTime (ZonedDateTime dateTime) {
      this.dateTime = dateTime;
      return this;
    }
    
    public ZonedDateTime getZonedDateTime () {
      return this.dateTime;
    }
  }
}
