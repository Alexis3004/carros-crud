package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

public class CarroServiceStub {

	private static HashMap<Long, Carro> carros = new HashMap<>();
	private static Long index = 3L;

	static {
		carros.put(1L, new Carro(1L, "picanto", "kia"));
		carros.put(2L, new Carro(2L, "spark", "renault"));
		carros.put(3L, new Carro(3L, "mazda 3", "mazda"));
	}

	public static ArrayList<Carro> getAllCarros() {
		return new ArrayList<>(carros.values());
	}

	public static Carro getCarro(Long id) {
		return carros.get(id);
	}

	public static Carro addCarro(Carro carro) {
		index += 1;
		carro.setId(index);
		carros.put(index, carro);
		return carro;
	}

	public static Carro updateCarro(Long id, Carro carro) {
		carro.setId(id);
		carros.put(id, carro);
		return carro;
	}

	public static Carro deleteCarro(Long id) {
		return carros.remove(id);
	}
}
