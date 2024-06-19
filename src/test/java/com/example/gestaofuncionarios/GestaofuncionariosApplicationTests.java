package com.example.gestaofuncionarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.gestaofuncionarios.model.Departamento;

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
    public void testGetAllDepartamentos() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/departamentos", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

	@Test
	public void testCreateDepartamento() {
		Departamento newDepartamento = new Departamento();
		newDepartamento.setNome("Finance");
		newDepartamento.setLocal("Building 1");

		ResponseEntity<Departamento> response = restTemplate.postForEntity("http://localhost:" + port + "/departamentos", newDepartamento, Departamento.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getNome()).isEqualTo(newDepartamento.getNome());
		assertThat(response.getBody().getLocal()).isEqualTo(newDepartamento.getLocal());
	}

	@Test
	public void testUpdateDepartamento() {
		Long idToUpdate = 1L; // substitua por um ID do banco de dados
		Departamento updatedDepartamento = new Departamento();
		updatedDepartamento.setNome("HR");
		updatedDepartamento.setLocal("Building 2");

		restTemplate.put("http://localhost:" + port + "/departamentos/" + idToUpdate, updatedDepartamento, Departamento.class);

		ResponseEntity<Departamento> response = restTemplate.getForEntity("http://localhost:" + port + "/departamentos/" + idToUpdate, Departamento.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getNome()).isEqualTo(updatedDepartamento.getNome());
		assertThat(response.getBody().getLocal()).isEqualTo(updatedDepartamento.getLocal());
	}

	@Test
	public void testDeleteDepartamento() {
		Long idToDelete = 1L; // substitua por um ID do banco de dados

		restTemplate.delete("http://localhost:" + port + "/departamentos/" + idToDelete);

		ResponseEntity<Departamento> response = restTemplate.getForEntity("http://localhost:" + port + "/departamentos/" + idToDelete, Departamento.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
