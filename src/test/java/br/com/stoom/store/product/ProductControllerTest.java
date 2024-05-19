package br.com.stoom.store.product;

import br.com.stoom.store.IntegrationTestInitializer;
import br.com.stoom.store.dto.CustomPageImpl;
import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe responsável por realizar os testes de integração
 * relacionado a entidade Product.
 *
 * @author Yuri Rennan <yuri.rennan14@gmail.com>
 */
public class ProductControllerTest extends IntegrationTestInitializer {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToCreateAProduct() {
        this.createBrand();
        this.createCategory();

        final String resourceLocation = "/api/products/";

        final CreateProductRequestDTO createProductRequestDTO = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> httpEntity = new HttpEntity<>(createProductRequestDTO);

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
    public void shouldBeAbleToListAnActiveProduct() {
        this.createBrand();
        this.createCategory();

        final String resourceLocation = "/api/products/";

        final CreateProductRequestDTO createProductRequestDTO = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> httpEntity = new HttpEntity<>(createProductRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listCategoryResource = "/api/products/1";

        final ResponseEntity<ReadProductResponseDTO> readProductResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        httpEntity,
                        ReadProductResponseDTO.class
                );

        final ReadProductResponseDTO body = readProductResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertEquals("Test Name", body.getName());
        assertEquals("Test Sku", body.getSku());
        assertEquals(BigDecimal.valueOf(10.99), body.getPrice());
        assertEquals(1L, body.getBrandId(), 0);
        assertEquals(1L, body.getCategoryId(), 0);
        assertTrue(body.isActive());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToDeactivateAProduct() {
        this.createBrand();
        this.createCategory();

        final String resourceLocation = "/api/products/";

        final CreateProductRequestDTO createProductRequestDTO = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> httpEntity = new HttpEntity<>(createProductRequestDTO);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final String listCategoryResource = "/api/products/1";

        final ResponseEntity<ReadProductResponseDTO> readProductResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        final ReadProductResponseDTO body = readProductResponseDTOResponseEntity.getBody();
        assertNotNull(body);

        assertTrue(body.isActive());

        final UpdateProductStatusDTO updateProductStatusDTO = new UpdateProductStatusDTO(false);

        final HttpEntity<UpdateProductStatusDTO> updateProductStatusDTOHttpEntity =
                new HttpEntity<>(updateProductStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.PUT,
                        updateProductStatusDTOHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);


        final ResponseEntity<ReadProductResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        listCategoryResource,
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToActivateAProduct() {
        this.createBrand();
        this.createCategory();

        final String resourceProductLocation = "/api/products";

        final CreateProductRequestDTO createProductRequest = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> creteProductHttpEntity = new HttpEntity<>(createProductRequest);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceProductLocation,
                        HttpMethod.POST,
                        creteProductHttpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final ResponseEntity<ReadProductResponseDTO> readProductResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        final ReadProductResponseDTO createProductResponseBody = readProductResponseDTOResponseEntity.getBody();
        assertNotNull(createProductResponseBody);
        assertTrue(createProductResponseBody.isActive());

        final UpdateProductStatusDTO updateProductStatusDTO = new UpdateProductStatusDTO(false);

        final HttpEntity<UpdateProductStatusDTO> updateProductHttpEntity =
                new HttpEntity<>(updateProductStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.PUT,
                        updateProductHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);


        final ResponseEntity<ReadProductResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);

        final UpdateProductStatusDTO updateProductStatusDTO1 = new UpdateProductStatusDTO(true);

        final HttpEntity<UpdateProductStatusDTO> updateProductHttpEntity1 =
                new HttpEntity<>(updateProductStatusDTO1);

        final ResponseEntity<Void> updateResponse1 = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.PUT,
                        updateProductHttpEntity1,
                        Void.class
                );

        assertEquals(updateResponse1.getStatusCode(), HttpStatus.NO_CONTENT);

        final ResponseEntity<ReadProductResponseDTO> readProductResponseDTOResponseEntity1 = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        final ReadProductResponseDTO bodyResponse = readProductResponseDTOResponseEntity1.getBody();
        assertNotNull(bodyResponse);

        assertEquals("Test Name", bodyResponse.getName());
        assertEquals("Test Sku", bodyResponse.getSku());
        assertEquals(BigDecimal.valueOf(10.99), bodyResponse.getPrice());
        assertEquals(1L, bodyResponse.getBrandId(), 0);
        assertEquals(1L, bodyResponse.getCategoryId(), 0);
        assertTrue(bodyResponse.isActive());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldNotBeAbleToListADeactivateProduct() {
        this.createBrand();
        this.createCategory();

        final String resourceProductLocation = "/api/products";

        final CreateProductRequestDTO createProductRequest = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> creteProductHttpEntity = new HttpEntity<>(createProductRequest);

        final ResponseEntity<Void> response = this.testRestTemplate
                .exchange(
                        resourceProductLocation,
                        HttpMethod.POST,
                        creteProductHttpEntity,
                        Void.class
                );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final ResponseEntity<ReadProductResponseDTO> readProductResponseDTOResponseEntity = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        final ReadProductResponseDTO createProductResponseBody = readProductResponseDTOResponseEntity.getBody();
        assertNotNull(createProductResponseBody);
        assertTrue(createProductResponseBody.isActive());

        final UpdateProductStatusDTO updateProductStatusDTO = new UpdateProductStatusDTO(false);

        final HttpEntity<UpdateProductStatusDTO> updateProductHttpEntity =
                new HttpEntity<>(updateProductStatusDTO);

        final ResponseEntity<Void> updateResponse = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.PUT,
                        updateProductHttpEntity,
                        Void.class
                );

        assertEquals(updateResponse.getStatusCode(), HttpStatus.NO_CONTENT);


        final ResponseEntity<ReadProductResponseDTO> readResponse = this.testRestTemplate
                .exchange(
                        resourceProductLocation + "/1",
                        HttpMethod.GET,
                        null,
                        ReadProductResponseDTO.class
                );

        assertEquals(readResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldBeAbleToListAllProducts() {
        this.createBrand();
        this.createCategory();

        final String resourceLocation = "/api/products";

        final CreateProductRequestDTO createProductRequestDTO = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> httpEntity = new HttpEntity<>(createProductRequestDTO);

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

        final ResponseEntity<CustomPageImpl<ReadProductResponseDTO>> productsPage = this.testRestTemplate
                .exchange(
                        resourceLocation + "?page=0&size=5",
                        HttpMethod.GET,
                        httpEntity,
                        new ParameterizedTypeReference<CustomPageImpl<ReadProductResponseDTO>>() {}
                );

        final Page<ReadProductResponseDTO> responseBody = productsPage.getBody();
        assertNotNull(responseBody);

        final List<ReadProductResponseDTO> products = responseBody.getContent();
        assertEquals(2, products.size());
    }

    @Test
    @Sql("/database/clear_database.sql")
    public void shouldNotBeAbleToListDeactivateProducts() {
        this.createBrand();
        this.createCategory();

        final String resourceLocation = "/api/products";

        final CreateProductRequestDTO createProductRequestDTO = this.getProductRequesDTO();

        final HttpEntity<CreateProductRequestDTO> httpEntity = new HttpEntity<>(createProductRequestDTO);

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

        final UpdateProductStatusDTO updateProductStatusDTO = new UpdateProductStatusDTO(false);

        final HttpEntity<UpdateProductStatusDTO> updateProductHttpEntity =
                new HttpEntity<>(updateProductStatusDTO);

        this.testRestTemplate
                .exchange(
                        resourceLocation + "/2",
                        HttpMethod.PUT,
                        updateProductHttpEntity,
                        Void.class
                );

        final ResponseEntity<CustomPageImpl<ReadProductResponseDTO>> productsPage = this.testRestTemplate
                .exchange(
                        resourceLocation + "?page=0&size=5",
                        HttpMethod.GET,
                        httpEntity,
                        new ParameterizedTypeReference<CustomPageImpl<ReadProductResponseDTO>>() {
                        }
                );

        final Page<ReadProductResponseDTO> responseBody = productsPage.getBody();
        assertNotNull(responseBody);

        final List<ReadProductResponseDTO> products = responseBody.getContent();
        assertEquals(1, products.size());

        final ReadProductResponseDTO readProductResponseDTO = products.get(0);
        assertEquals(1L, readProductResponseDTO.getId(), 0);
    }

    private CreateProductRequestDTO getProductRequesDTO() {
        final CreateProductRequestDTO productRequestDTO = new CreateProductRequestDTO();

        productRequestDTO.setName("Test Name");
        productRequestDTO.setSku("Test Sku");
        productRequestDTO.setPrice(BigDecimal.valueOf(10.99));
        productRequestDTO.setBrandId(1L);
        productRequestDTO.setCategoryId(1L);

        return productRequestDTO;
    }

    private CreateBrandRequestDTO getBrandRequesDTO() {
        final CreateBrandRequestDTO brandRequestDTO = new CreateBrandRequestDTO();

        brandRequestDTO.setName("Test Name");
        brandRequestDTO.setDescription("Test Description");

        return brandRequestDTO;
    }

    private CreateCategoryRequestDTO getCategoryRequesDTO() {
        final CreateCategoryRequestDTO categoryRequestDTO = new CreateCategoryRequestDTO();

        categoryRequestDTO.setName("Test Name");
        categoryRequestDTO.setDescription("Test Description");

        return categoryRequestDTO;
    }

    private void createBrand(){
        final String resourceLocation = "/api/brands/";

        final CreateBrandRequestDTO createBrandRequestDTO = this.getBrandRequesDTO();

        final HttpEntity<CreateBrandRequestDTO> httpEntity = new HttpEntity<>(createBrandRequestDTO);

        this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );
    }

    private void createCategory(){
        final String resourceLocation = "/api/categories/";

        final CreateCategoryRequestDTO createCategoryRequest = this.getCategoryRequesDTO();

        final HttpEntity<CreateCategoryRequestDTO> httpEntity = new HttpEntity<>(createCategoryRequest);

        this.testRestTemplate
                .exchange(
                        resourceLocation,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class
                );
    }

}
