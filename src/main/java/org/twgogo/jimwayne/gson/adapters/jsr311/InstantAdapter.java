package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.lang.reflect.Type;
import java.time.Instant;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize and de-serialize {@link Instant} to and from epoch time. 
 */
public class InstantAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {
  @Override
  public JsonElement serialize (Instant time, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(time.toEpochMilli());
  }

  @Override
  public Instant deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    long epochTime = json.getAsLong();
    return Instant.ofEpochMilli(epochTime);
  }
}