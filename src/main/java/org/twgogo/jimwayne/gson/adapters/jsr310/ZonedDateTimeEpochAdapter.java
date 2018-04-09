package org.twgogo.jimwayne.gson.adapters.jsr310;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
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
 * Serialize and de-serialize {@link ZonedDateTime} to and from epoch
 * milliseconds.
 */
public class ZonedDateTimeEpochAdapter
    implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
  private ZoneId zoneId;
  
  public ZonedDateTimeEpochAdapter (ZoneId id) {
    this.zoneId = id;
  }
  
  @Override
  public JsonElement serialize(ZonedDateTime dateTime, Type typeOfSrc,
      JsonSerializationContext context) {
    return new JsonPrimitive(dateTime.toInstant().toEpochMilli());
  }

  @Override
  public ZonedDateTime deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    long epochMillis = json.getAsLong();
    return ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(epochMillis), this.zoneId);
  }
}
