package com.cars.cars.api;

import models.Baralho;
import models.Carta;
import models.DataBrandCar;
import services.ConsumoAPI;
import services.ConverterDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DisplayImageInTerminal {

	static ConverterDados converterDados = new ConverterDados();

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Digite a quantidade de jogadores");
		int numero_jogadores = scanner.nextInt();
		scanner.nextLine();
		List<String> jogadores = new ArrayList<>();

		for(int i =0;  i<numero_jogadores;i ++){
			System.out.println("Digite o nome do jogador "+ i+": ");
			String name = scanner.nextLine();
			jogadores.add(name);
		}

		System.out.println(jogadores);

		jogadores.forEach(jogador->{
			System.out.println("Gerando cartas para o jogador:" + jogador);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("...carregando");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			var listaCartas = ConsumoAPI.obterDados("https://deckofcardsapi.com/api/deck/omo5lwejh5va/draw/?count=2");

			var listaCartasConvertida = converterDados.obterDados(listaCartas, Baralho.class);

			var teste = listaCartasConvertida.cards().stream().map(DisplayImageInTerminal::formatCard).collect(Collectors.toList());

			teste.forEach(e-> System.out.print(e.cardFormat()));

			System.out.println(" \n Digite 1 para passar a vez para o proximo jogardo para que ele possa ver suas cartas tambem:");
			var proximo =scanner.nextInt();

			while (proximo!=1){
				proximo =scanner.nextInt();
			}
		});




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
