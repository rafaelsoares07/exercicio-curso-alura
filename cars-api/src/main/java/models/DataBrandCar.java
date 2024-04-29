package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataBrandCar(
        @JsonProperty("codigo") int code,
        @JsonProperty("nome") String name
) {
}
