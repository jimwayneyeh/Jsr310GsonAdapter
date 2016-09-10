package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize and de-serialize {@link LocalDate} to and from ISO-8601 representation. 
 */
public class LocalDateIsoAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
  @Override
  public JsonElement serialize (LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(date.toString());
  }

  @Override
  public LocalDate deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String isoPresentation = json.getAsString();
    return LocalDate.from(DateTimeFormatter.ISO_DATE.parse(isoPresentation));
  }
}