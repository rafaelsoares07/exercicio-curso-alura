package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataCar(
        @JsonProperty("Valor") String value,
        @JsonProperty("Marca") String brand,
        @JsonProperty("Modelo") String model,
        @JsonProperty("AnoModelo") Integer year,
        @JsonProperty("Combustivel") String fuel
) {
}
