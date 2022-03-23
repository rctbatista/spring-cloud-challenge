package br.com.caelum.eats.restaurante.controller.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

import br.com.caelum.eats.restaurante.repository.entity.HorarioDeFuncionamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDeFuncionamentoDto {

	private Long id;

	private DayOfWeek diaDaSemana;

	private LocalTime horarioDeAbertura;

	private LocalTime horarioDeFechamento;

	public HorarioDeFuncionamentoDto(HorarioDeFuncionamento horario) {
		this(horario.getId(), horario.getDiaDaSemana(), horario.getHorarioDeAbertura(), horario.getHorarioDeFechamento());
	}
}
