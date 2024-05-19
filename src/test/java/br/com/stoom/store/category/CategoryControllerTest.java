package br.com.stoom.store.category;

import br.com.stoom.store.IntegrationTestInitializer;
import br.com.stoom.store.dto.CustomPageImpl;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToCreateACategory() {
        final String resourceLocation = "/api/categories/";

        final CreateCategoryRequestDTO createCategoryRequest = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> httpEntity = new HttpEntity<>(createCategoryRequest);

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
    public void shouldBeAbleToListAnActiveCategory() {
        final String resourceLocation = "/api/categories/";

        final CreateCategoryRequestDTO categoryRequestDTO = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> httpEntity = new HttpEntity<>(categoryRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listCategoryResource = "/api/categories/1";

        final ResponseEntity<ReadCategoryResponseDTO> readCategoryResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadCategoryResponseDTO.class
                );

        final ReadCategoryResponseDTO body = readCategoryResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Description", body.getDescription());
        assertTrue(body.isActive());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToDeactivateACategory() {
        final String resourceLocation = "/api/categories/";

        final CreateCategoryRequestDTO categoryRequestDTO = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> createCategoryRequestDTOHttpEntity
                = new HttpEntity<>(categoryRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        createCategoryRequestDTOHttpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listCategoryResource = "/api/categories/1";

        final ResponseEntity<ReadCategoryResponseDTO> readCategoryResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        null,
                        ReadCategoryResponseDTO.class
                );

        final ReadCategoryResponseDTO body = readCategoryResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertTrue(body.isActive());

        final UpdateCategoryStatusDTO updateCategoryStatusDTO = new UpdateCategoryStatusDTO(false);

        final HttpEntity<UpdateCategoryStatusDTO> updateCategoryStatusDTOHttpEntity =
                new HttpEntity<>(updateCategoryStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.PUT,
                        updateCategoryStatusDTOHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadCategoryResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        null,
                        ReadCategoryResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToActivateACategory() {
        final String resourceLocation = "/api/categories/";

        final CreateCategoryRequestDTO categoryRequestDTO = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> createCategoryRequestDTOHttpEntity
                = new HttpEntity<>(categoryRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        createCategoryRequestDTOHttpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);


        final String listCategoriesResource = "/api/categories/1";

        final ResponseEntity<ReadCategoryResponseDTO> readCategoryResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listCategoriesResource,
                        HttpMethod.GET,
                        null,
                        ReadCategoryResponseDTO.class
                );

        final ReadCategoryResponseDTO body = readCategoryResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Description", body.getDescription());
        assertTrue(body.isActive());

        final UpdateCategoryStatusDTO updateCategoryStatusDTO = new UpdateCategoryStatusDTO(false);

        final HttpEntity<UpdateCategoryStatusDTO> updateCategoryStatusDTOHttpEntity =
                new HttpEntity<>(updateCategoryStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listCategoriesResource,
                        HttpMethod.PUT,
                        updateCategoryStatusDTOHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadCategoryResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        listCategoriesResource,
                        HttpMethod.GET,
                        null,
                        ReadCategoryResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);

        final UpdateCategoryStatusDTO updateCategoryStatusDTO1 = new UpdateCategoryStatusDTO(true);

        final HttpEntity<UpdateCategoryStatusDTO> updateCategoryStatusDTOHttpEntity1 =
                new HttpEntity<>(updateCategoryStatusDTO1);

        final ResponseEntity<Void> updateResponse2 = this.testRestTemplate
                .exchange(
                        listCategoriesResource,
                        HttpMethod.PUT,
                        updateCategoryStatusDTOHttpEntity1,
                        Void.class
                );

        assertEquals(updateResponse2.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadCategoryResponseDTO> readCategoryResponseDTOResponseEntity1 = this.testRestTemplate
                .exchange(
                        listCategoriesResource,
                        HttpMethod.GET,
                        null,
                        ReadCategoryResponseDTO.class
                );

        final ReadCategoryResponseDTO responseDTO = readCategoryResponseDTOResponseEntity1.getBody();
        assertNotNull(responseDTO);

        assertEquals("Test Name", responseDTO.getName());
        assertEquals("Test Description", responseDTO.getDescription());
        assertTrue(responseDTO.isActive());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldNotBeAbleToListADesactiveCategory() {
        final String resourceLocation = "/api/categories/";

        final CreateCategoryRequestDTO createCategoryRequestDTO = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> httpEntity = new HttpEntity<>(createCategoryRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listCategoryResource = "/api/categories/1";

        final ResponseEntity<ReadCategoryResponseDTO> readCategoryResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadCategoryResponseDTO.class
                );

        final ReadCategoryResponseDTO body = readCategoryResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Description", body.getDescription());
        assertTrue(body.isActive());


        final UpdateCategoryStatusDTO updateCategoryStatusDTO = new UpdateCategoryStatusDTO(false);

        final HttpEntity<UpdateCategoryStatusDTO> updateCategoryStatusDTOHttpEntity =
                new HttpEntity<>(updateCategoryStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.PUT,
                        updateCategoryStatusDTOHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadCategoryResponseDTO> readCategoryResponseDTOResponseEntity1 = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadCategoryResponseDTO.class
                );

        assertEquals(readCategoryResponseDTOResponseEntity1.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToListAllCategories() {
        final String resourceLocation = "/api/categories";

        final CreateCategoryRequestDTO createCategoryRequestDTO = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> httpEntity = new HttpEntity<>(createCategoryRequestDTO);

        this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        final ResponseEntity<CustomPageImpl<ReadCategoryResponseDTO>> categoriesPage = this.testRestTemplate
                .exchange(
                        resourceLocation + "?page=0&size=5",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<CustomPageImpl<ReadCategoryResponseDTO>>() {}
                );

        final Page<ReadCategoryResponseDTO> responseBody = categoriesPage.getBody();
        assertNotNull(responseBody);

        final List<ReadCategoryResponseDTO> categories = responseBody.getContent();
        assertEquals(2, categories.size());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldNotBeAbleToListDeactivateCategories() {
        final String resourceLocation = "/api/categories";

        final CreateCategoryRequestDTO createCategoryRequestDTO = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> httpEntity = new HttpEntity<>(createCategoryRequestDTO);

        this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        final UpdateCategoryStatusDTO updateCategoryStatusDTO = new UpdateCategoryStatusDTO(false);

        final HttpEntity<UpdateCategoryStatusDTO> updateCategoryStatusDTOHttpEntity =
                new HttpEntity<>(updateCategoryStatusDTO);

        this.testRestTemplate
                .exchange(
                        resourceLocation + "/2",
                        HttpMethod.PUT,
                        updateCategoryStatusDTOHttpEntity,
                        Void.class
                );

        final ResponseEntity<CustomPageImpl<ReadCategoryResponseDTO>> categoriesPage = this.testRestTemplate
                .exchange(
                        resourceLocation + "?page=0&size=5",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<CustomPageImpl<ReadCategoryResponseDTO>>() {}
                );

        final Page<ReadCategoryResponseDTO> responseBody = categoriesPage.getBody();
        assertNotNull(responseBody);

        final List<ReadCategoryResponseDTO> categories = responseBody.getContent();
        assertEquals(1, categories.size());

        final ReadCategoryResponseDTO readCategoryResponseDTO = categories.get(0);
        assertEquals(1L, readCategoryResponseDTO.getId(), 0);
    }

    private CreateCategoryRequestDTO getCategoryRequesDTO() {
        final CreateCategoryRequestDTO categoryRequestDTO = new CreateCategoryRequestDTO();

        categoryRequestDTO.setName("Test Name");
        categoryRequestDTO.setDescription("Test Description");

        return categoryRequestDTO;
    }

}
