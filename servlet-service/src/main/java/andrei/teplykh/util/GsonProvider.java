package andrei.teplykh.util;

import com.google.gson.*;

import javax.enterprise.inject.Produces;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GsonProvider {

    @Produces
    @GsonQualifier
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new DateConverter())
            .create();

    private static class DateConverter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public LocalDateTime deserialize(final JsonElement jsonElement, final Type type,
                                         final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement != null) {
                return ZonedDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString()).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            } else {
                return null;
            }
        }

        @Override
        public JsonElement serialize(final LocalDateTime localDateTime, final Type type,
                                     final JsonSerializationContext jsonSerializationContext) {
            if (localDateTime != null) {
                return new JsonPrimitive(FORMATTER.format(localDateTime));
            } else {
                return null;
            }
        }
    }
}
