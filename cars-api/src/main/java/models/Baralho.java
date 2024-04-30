package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Baralho(
        @JsonProperty("deck-id") String deck_id,
        @JsonProperty("cards") List<Carta> cards
) {
}
