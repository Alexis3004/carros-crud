package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CarroController {

	@GetMapping("/getCarros")
	public ArrayList<Carro> getAllCarros() {
		ArrayList<Carro> carros = CarroServiceStub.getAllCarros();
		if(carros.isEmpty()){
			// throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"No existen carros");
			throw new CarrosNotFound("No existen carros");
		}
		return carros;
	}

	@GetMapping("/getCarro/{id}")
	public Carro getCarro(@PathVariable Long id) {
		Carro carro = CarroServiceStub.getCarro(id);
		if(carro == null){
			// throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"No existen ese carro");
			throw new CarrosNotFound(String.format("No existe el carro con id %s", id));
		}
		return carro;
	}

	@PostMapping("/postCarro")
	public Carro addCarro(@RequestBody Carro carro) {
		return CarroServiceStub.addCarro(carro);
	}

	@PutMapping("/putCarro/{id}")
	public Carro updateCarro(@PathVariable long id, @RequestBody Carro carroModificado) {
		Carro carro = CarroServiceStub.getCarro(id);
		if(carro == null){
			// throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"El carro a modificar no existe");
			throw new CarrosNotFound(String.format("El carro con id %s que desea modificar no existe", id));
		}
		return CarroServiceStub.updateCarro(id, carroModificado);
	}

	@DeleteMapping("/deleteCarro/{id}")
	public Carro deleteCarro(@PathVariable long id) {
		Carro carro = CarroServiceStub.getCarro(id);
		if(carro == null){
			// throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"El carro a eliminar no existe");
			throw new CarrosNotFound(String.format("El carro con id %s que desea eliminar no existe", id));
		}
		return CarroServiceStub.deleteCarro(id);
	}
}
