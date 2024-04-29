package com.cars.cars.api;

import models.DataBrandCar;
import models.DataModelsCar;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import services.ConsumoAPI;
import services.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CarsApiApplication implements CommandLineRunner {

	private ConsumoAPI consumoAPI = new ConsumoAPI();
	ConverterDados converterDados = new ConverterDados();

	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(CarsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Funcionando");

		var data = consumoAPI.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas");

		var marcas = converterDados.obterLista(data, DataBrandCar.class);

		marcas.stream().sorted(Comparator.comparing(DataBrandCar::code)).forEach(System.out::println);

		System.out.println("Informe o código da marca para consultar os modelos:");
		var codMarca = scanner.nextInt();

		var dataModels = consumoAPI.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codMarca+"/modelos");

		var modelos = converterDados.obterDados(dataModels, DataModelsCar.class);

		modelos.modelsCar().forEach(System.out::println);

	}
}
