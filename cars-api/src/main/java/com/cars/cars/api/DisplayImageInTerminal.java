package com.cars.cars.api;

import models.Baralho;
import models.Carta;
import services.ConsumoAPI;
import services.ConverterDados;

import java.io.IOException;
import java.util.stream.Collectors;

public class DisplayImageInTerminal {

	static ConverterDados converterDados = new ConverterDados();

	public static void main(String[] args) throws IOException, InterruptedException {

		var listaCartas = ConsumoAPI.obterDados("https://deckofcardsapi.com/api/deck/4gtcpb1r8807/draw/?count=5");

		var listaCartasConvertida = converterDados.obterDados(listaCartas, Baralho.class);

		var teste = listaCartasConvertida.cards().stream().map(DisplayImageInTerminal::formatCard).collect(Collectors.toList());

		teste.forEach(e-> System.out.print(e.cardFormat()));
	}

	public static Carta formatCard(Carta carta) {
		String icon = null;
		String value = carta.value();

		switch (carta.suit()) {
			case "HEARTS":
				icon = "♥";
				break;
			case "DIAMONDS":
				icon = "♦";
				break;
			case "SPADES":
				icon = "♠";
				break;
			case "CLUBS":
				icon = "♣";
				break;
			default:
				break;
		}

		switch (value) {
			case "KING":
				value = "K";
				break;
			case "ACE":
				value = "A";
				break;
			case "QUEEN":
				value = "Q";
				break;
			case "JACK":
				value = "J";
				break;
		}

		String cardFormat = """
    
				 - - - -
				|%s     |
				|  %s   | = 1
				|     %s|
				 - - - -
				""";

		cardFormat = cardFormat.stripTrailing().formatted(icon, value, icon);

		return new Carta(carta.code(), value, carta.suit(), cardFormat);
	}
}
