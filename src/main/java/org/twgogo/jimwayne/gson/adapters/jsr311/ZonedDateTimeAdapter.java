package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize and de-serialize {@link ZonedDateTime} to and from ISO-8601 representation. 
 */
public class ZonedDateTimeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
  @Override
  public JsonElement serialize (ZonedDateTime dateTime, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(dateTime.toString());
  }

  @Override
  public ZonedDateTime deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String isoPresentation = json.getAsString();
    return ZonedDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(isoPresentation));
  }
}