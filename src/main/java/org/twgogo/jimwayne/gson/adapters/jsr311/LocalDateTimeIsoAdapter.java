package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize and de-serialize {@link LocalDateTime} to and from ISO-8601 string.
 */
public class LocalDateTimeIsoAdapter
    implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
  
  @Override
  public JsonElement serialize(LocalDateTime datetime, Type typeOfSrc,
      JsonSerializationContext context) {
    return new JsonPrimitive(datetime.toString());
  }

  @Override
  public LocalDateTime deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    String isoString = json.getAsString();
    return LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(isoString));
  }
}