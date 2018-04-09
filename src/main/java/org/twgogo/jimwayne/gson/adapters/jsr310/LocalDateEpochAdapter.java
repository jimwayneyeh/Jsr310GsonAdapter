package org.twgogo.jimwayne.gson.adapters.jsr310;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize and de-serialize {@link LocalDate} to and from epoch day. 
 */
public class LocalDateEpochAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
  @Override
  public JsonElement serialize (LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(date.toEpochDay());
  }

  @Override
  public LocalDate deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    long epochDay = json.getAsLong();
    return LocalDate.ofEpochDay(epochDay);
  }
}