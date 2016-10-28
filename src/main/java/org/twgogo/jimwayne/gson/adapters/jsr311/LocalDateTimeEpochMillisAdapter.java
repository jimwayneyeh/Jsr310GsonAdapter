package org.twgogo.jimwayne.gson.adapters.jsr311;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize and de-serialize {@link LocalDateTime} to and from epoch millis.
 */
public class LocalDateTimeEpochMillisAdapter
    implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
  private ZoneOffset zoneOffset;
  
  public LocalDateTimeEpochMillisAdapter (ZoneOffset offset) {
    this.zoneOffset = offset;
  }
  
  @Override
  public JsonElement serialize(LocalDateTime datetime, Type typeOfSrc,
      JsonSerializationContext context) {
    return new JsonPrimitive(
        datetime.toInstant(this.zoneOffset).toEpochMilli());
  }

  @Override
  public LocalDateTime deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    long epochMillis = json.getAsLong();
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(epochMillis), this.zoneOffset);
  }
}