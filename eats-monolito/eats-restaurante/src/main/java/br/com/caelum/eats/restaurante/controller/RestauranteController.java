package br.com.caelum.eats.restaurante.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.restaurante.controller.domain.RestauranteDto;
import br.com.caelum.eats.restaurante.exception.ResourceNotFoundException;
import br.com.caelum.eats.restaurante.repository.RestauranteRepository;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;
import br.com.caelum.eats.restaurante.service.RestauranteService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RestauranteController {

	private final RestauranteRepository restauranteRepo;
	private final RestauranteService restauranteService;

	@GetMapping("/restaurantes/{id}")
	public RestauranteDto detalha(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	@GetMapping("/restaurantes")
	public List<RestauranteDto> detalhePorIds(@RequestParam("ids") List<Long> ids) {
		return restauranteRepo.findAllById(ids).stream().map(RestauranteDto::new).collect(Collectors.toList());
	}

	@GetMapping("/parceiros/restaurantes/{id}")
	public RestauranteDto detalhaParceiro(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	@PostMapping("/parceiros/restaurantes")
	public Restaurante adiciona(@RequestBody Restaurante restaurante) {
		return restauranteService.adiciona(restaurante);
	}

	@PutMapping("/parceiros/restaurantes/{id}")
	public RestauranteDto atualiza(@PathVariable Long id, @RequestBody RestauranteDto restaurante) {
		return restauranteService.atualiza(id, restaurante);
	}

	@GetMapping("/admin/restaurantes/em-aprovacao")
	public List<RestauranteDto> emAprovacao() {
		return restauranteRepo.findAllByAprovado(false).stream().map(RestauranteDto::new).collect(Collectors.toList());
	}

	@Transactional
	@PatchMapping("/admin/restaurantes/{id}")
	public void aprova(@PathVariable("id") Long id) {
		restauranteRepo.aprovaPorId(id);
	}
}
