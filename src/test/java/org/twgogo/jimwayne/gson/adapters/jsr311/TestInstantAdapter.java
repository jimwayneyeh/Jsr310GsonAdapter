package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.time.Instant;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestInstantAdapter {
  private JsonParser parser = new JsonParser();
  private Gson gson = new GsonBuilder()
      .registerTypeAdapter(Instant.class, new InstantAdapter())
      .create();
  
  @Test
  public void testSerialization () {
    MyObject myObj = new MyObject();
    Instant now = Instant.now();
    
    Assert.assertEquals(
        now.toEpochMilli(), 
        this.parser.parse(this.gson.toJson(myObj.setTime(now)))
            .getAsJsonObject()
            .get("time").getAsLong());
  }
  
  @Test
  public void testDeserialization () {
    MyObject myObj = null;
    JsonObject json = new JsonObject();
    
    long now = Instant.now().toEpochMilli();
    
    json.addProperty("time", now);
    myObj = this.gson.fromJson(json, MyObject.class);
    Assert.assertEquals(
        now, 
        myObj.getTime().toEpochMilli());
  }
  
  private class MyObject {
    private Instant time;
    
    public MyObject setTime (Instant time) {
      this.time = time;
      return this;
    }
    
    public Instant getTime () {
      return this.time;
    }
  }
}
