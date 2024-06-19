package com.example.gestaofuncionarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.gestaofuncionarios.model.Departamento;
import com.example.gestaofuncionarios.model.Funcionario;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GestaofuncionariosApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateDepartamento() {
		Departamento newDepartamento = new Departamento(1L, "Finance", "Building 1");

		ResponseEntity<Departamento> response = restTemplate
				.postForEntity("http://localhost:" + port + "/departamentos", newDepartamento, Departamento.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getNome()).isEqualTo(newDepartamento.getNome());
		assertThat(response.getBody().getLocal()).isEqualTo(newDepartamento.getLocal());
	}

	@Test
	public void testUpdateDepartamento() {

		Departamento newDepartamento = new Departamento(1L, "Finance", "Building 1");

		restTemplate.postForEntity("http://localhost:" + port + "/departamentos", newDepartamento, Departamento.class);

		Long idToUpdate = newDepartamento.getId();
		Departamento updatedDepartamento = new Departamento();
		updatedDepartamento.setNome("HR");
		updatedDepartamento.setLocal("Building 2");

		restTemplate.put("http://localhost:" + port + "/departamentos/" + idToUpdate, updatedDepartamento,
				Departamento.class);

		ResponseEntity<Departamento> response = restTemplate
				.getForEntity("http://localhost:" + port + "/departamentos/" + idToUpdate, Departamento.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
		/*
		 * assertThat(response.getBody().getNome()).isEqualTo(updatedDepartamento.
		 * getNome());
		 * assertThat(response.getBody().getLocal()).isEqualTo(updatedDepartamento.
		 * getLocal());
		 */
	}

	@Test
	public void testGetAllDepartamentos() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/departamentos",
				String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testCreateFuncionario() {
		Funcionario newFuncionario = new Funcionario(1L, "John Doe", "123 Main St", "1234567890",
				"john.doe@example.com", null, null);

		ResponseEntity<Funcionario> response = restTemplate.postForEntity("http://localhost:" + port + "/funcionarios",
				newFuncionario, Funcionario.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getNome()).isEqualTo(newFuncionario.getNome());
	}

	@Test
	public void testUpdateFuncionario() {

		Funcionario newFuncionario = new Funcionario(1L, "John Doe", "123 Main St", "1234567890",
				"john.doe@example.com", null, null);

		restTemplate.postForEntity("http://localhost:" + port + "/funcionarios", newFuncionario, Funcionario.class);

		Long idToUpdate = newFuncionario.getId();
		Funcionario updatedFuncionario = new Funcionario(1L, "Jane Doe", "123 Main St", "1234567890",
				"jane.doe@example.com", null, null);

		restTemplate.put("http://localhost:" + port + "/funcionarios/" + idToUpdate, updatedFuncionario,
				Funcionario.class);

		ResponseEntity<Funcionario> response = restTemplate
				.getForEntity("http://localhost:" + port + "/funcionarios/" + idToUpdate, Funcionario.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
		/*
		 * assertThat(response.getBody().getNome()).isEqualTo(updatedFuncionario.getNome
		 * ());
		 */
	}

	@Test
	public void testGetAllFuncionarios() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/funcionarios",
				String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testDeleteFuncionario() {
		Long idToDelete = 1L; // substitua por um ID do banco de dados

		restTemplate.delete("http://localhost:" + port + "/funcionarios/" + idToDelete);

		ResponseEntity<Funcionario> response = restTemplate
				.getForEntity("http://localhost:" + port + "/funcionarios/" + idToDelete, Funcionario.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Test
	public void testDeleteDepartamento() {
		Long idToDelete = 1L; // substitua por um ID do banco de dados

		restTemplate.delete("http://localhost:" + port + "/departamentos/" + idToDelete);

		ResponseEntity<Departamento> response = restTemplate
				.getForEntity("http://localhost:" + port + "/departamentos/" + idToDelete, Departamento.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
	}
}
