package br.com.caelum.eats.restaurante.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.restaurante.controller.domain.CategoriaDoCardapioDto;
import br.com.caelum.eats.restaurante.exception.ResourceNotFoundException;
import br.com.caelum.eats.restaurante.repository.CategoriaDoCardapioRepository;
import br.com.caelum.eats.restaurante.repository.entity.CategoriaDoCardapio;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CategoriaDoCardapioController {

	private CategoriaDoCardapioRepository repo;

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}")
	public CategoriaDoCardapioDto categoriaPorId(@PathVariable("idCategoria") Long idCategoria) {
		CategoriaDoCardapio categoria = repo.findById(idCategoria).orElseThrow(() -> new ResourceNotFoundException());
		return new CategoriaDoCardapioDto(categoria);
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria")
	public CategoriaDoCardapioDto cardapioDoRestaurante(@PathVariable("idCardapio") Long idCardapio,
			@RequestBody CategoriaDoCardapio categoria) {
		return new CategoriaDoCardapioDto(repo.save(categoria));
	}

}
