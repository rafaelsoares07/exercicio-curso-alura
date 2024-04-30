package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Carta(
        @JsonProperty("code") String code,
        @JsonProperty("value") String value,
        @JsonProperty("suit") String suit,
        String cardFormat
) {

}
