package util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GsonProvider {
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new DateConverter())
            .create();

    private static class DateConverter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement != null) {
                return ZonedDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString()).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            } else {
                return null;
            }
        }

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            if (localDateTime != null) {
                return new JsonPrimitive(FORMATTER.format(localDateTime));
            } else {
                return null;
            }
        }
    }
}
