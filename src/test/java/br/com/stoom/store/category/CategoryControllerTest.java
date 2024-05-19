package br.com.stoom.store.category;

import br.com.stoom.store.IntegrationTestInitializer;
import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.brand.ReadBrandResponseDTO;
import br.com.stoom.store.dto.brand.UpdateBrandStatusDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.*;

/**
 * Classe responsável por realizar os testes de integração
 * relacionado a entidade Category.
 *
 * @author Yuri Rennan <yuri.rennan14@gmail.com>
 */
public class CategoryControllerTest extends IntegrationTestInitializer {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldBeAbleToCreateAnBrand() {
        final String resourceLocation = "/api/brands/";

        final CreateBrandRequestDTO brandRequestDTO = this.getBrandRequesDTO();

        final HttpEntity<CreateBrandRequestDTO> httpEntity = new HttpEntity<>(brandRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToListAActiveBrand() {
        final String resourceLocation = "/api/brands/";

        final CreateBrandRequestDTO brandRequestDTO = this.getBrandRequesDTO();

        final HttpEntity<CreateBrandRequestDTO> httpEntity = new HttpEntity<>(brandRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listBrandResource = "/api/brands/1";

        final ResponseEntity<ReadBrandResponseDTO> readBrandResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadBrandResponseDTO.class
                );

        final ReadBrandResponseDTO body = readBrandResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Description", body.getDescription());
        assertTrue(body.isActive());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToDeactivateAnBrand() {
        final String resourceLocation = "/api/brands/";

        final CreateBrandRequestDTO brandRequestDTO = this.getBrandRequesDTO();

        final HttpEntity<CreateBrandRequestDTO> createBrandRequestDTOHttpEntity = new HttpEntity<>(brandRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        createBrandRequestDTOHttpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listBrandResource = "/api/brands/1";

        final ResponseEntity<ReadBrandResponseDTO> readBrandResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        null,
                        ReadBrandResponseDTO.class
                );

        final ReadBrandResponseDTO body = readBrandResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertTrue(body.isActive());

        final UpdateBrandStatusDTO updateBrandStatusDTO = new UpdateBrandStatusDTO(false);

        final HttpEntity<UpdateBrandStatusDTO> updateBrandStatusDTOHttpEntity =
                new HttpEntity<>(updateBrandStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.PUT,
                        updateBrandStatusDTOHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadBrandResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        null,
                        ReadBrandResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToActivateAnBrand() {
        final String resourceLocation = "/api/brands/";

        final CreateBrandRequestDTO brandRequestDTO = this.getBrandRequesDTO();

        final HttpEntity<CreateBrandRequestDTO> createBrandRequestDTOHttpEntity = new HttpEntity<>(brandRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        createBrandRequestDTOHttpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listBrandResource = "/api/brands/1";

        final ResponseEntity<ReadBrandResponseDTO> readBrandResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        null,
                        ReadBrandResponseDTO.class
                );

        final ReadBrandResponseDTO body = readBrandResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Description", body.getDescription());
        assertTrue(body.isActive());

        final UpdateBrandStatusDTO updateBrandStatusDTO = new UpdateBrandStatusDTO(false);

        final HttpEntity<UpdateBrandStatusDTO> updateBrandStatusDTOHttpEntity =
                new HttpEntity<>(updateBrandStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.PUT,
                        updateBrandStatusDTOHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadBrandResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        null,
                        ReadBrandResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);

        final UpdateBrandStatusDTO updateBrandStatusDTO1 = new UpdateBrandStatusDTO(true);

        final HttpEntity<UpdateBrandStatusDTO> updateBrandStatusDTOHttpEntity1 =
                new HttpEntity<>(updateBrandStatusDTO1);

        final ResponseEntity<Void> updateResponse2 = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.PUT,
                        updateBrandStatusDTOHttpEntity1,
                        Void.class
                );

        assertEquals(updateResponse2.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadBrandResponseDTO> readBrandResponseDTOResponseEntity1 = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        null,
                        ReadBrandResponseDTO.class
                );

        final ReadBrandResponseDTO responseDTO = readBrandResponseDTOResponseEntity1.getBody();
        assertNotNull(responseDTO);

        assertEquals("Test Name", responseDTO.getName());
        assertEquals("Test Description", responseDTO.getDescription());
        assertTrue(responseDTO.isActive());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldNotBeAbleToListADesactiveBrand() {
        final String resourceLocation = "/api/brands/";

        final CreateBrandRequestDTO createBrandRequestDTO = this.getBrandRequesDTO();

        final HttpEntity<CreateBrandRequestDTO> httpEntity = new HttpEntity<>(createBrandRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listBrandResource = "/api/brands/1";

        final ResponseEntity<ReadBrandResponseDTO> readBrandResponse = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadBrandResponseDTO.class
                );

        final ReadBrandResponseDTO body = readBrandResponse.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Description", body.getDescription());
        assertTrue(body.isActive());


        final UpdateBrandStatusDTO updateBrandStatusDTO = new UpdateBrandStatusDTO(false);

        final HttpEntity<UpdateBrandStatusDTO> updateBrandEntity =
                new HttpEntity<>(updateBrandStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.PUT,
                        updateBrandEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadBrandResponseDTO> readBrandResponse2 = this.testRestTemplate
                .exchange(
                        listBrandResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadBrandResponseDTO.class
                );

        assertEquals(readBrandResponse2.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    private CreateBrandRequestDTO getBrandRequesDTO() {
        final CreateBrandRequestDTO brandRequestDTO = new CreateBrandRequestDTO();

        brandRequestDTO.setName("Test Name");
        brandRequestDTO.setDescription("Test Description");

        return brandRequestDTO;
    }

}
