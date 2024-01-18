package com.sa.quotes.customers.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;


@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    
    
    @Bean
    public OpenAPI getApiInfo() {
    
        ApiResponse okAPI = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 200, \"Status\" : \"OK\", \"Message\" : \"Obtenido con exito!\"}")))
        );
        ApiResponse noContentApiResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 204, \"Status\" : \"NO_CONTENT\", \"Message\" : \"Eliminado con éxito!\"}")))
        );
        ApiResponse createdApiResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 201, \"Status\" : \"CREATED\", \"Message\" : \"Creado con éxito!\"}")))
        );
                ApiResponse badRequestApiResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 400, \"Status\" : \"BAD_REQUEST\", \"Message\" : \"Petición errónea, verificar.\"}")))
        );
                ApiResponse notFoundApiResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 404, \"Status\" : \"NOT_FOUND\", \"Message\" : \"No se encontró el registro seleccionado.\"}")))
        );
        
                ApiResponse internalServerErrorApiResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 500, \"Status\" : \"INTERNAL_SERVER_ERROR\", \"Message\" : \"Error del servidor.\"}")))
        );
      /*          ApiResponse conflictApiResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                    new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                            new Example().value("{\"code\" : 409, \"Status\" : \"CONFLICT\", \"Message\" : \"Error: El recurso ya existe.\"}")))
        ); */
                
        //internalServerErrorApiResponse
        Components components = new Components();
        components.addResponses("okAPI", okAPI);
        components.addResponses("noContentApiResponse", noContentApiResponse);
        components.addResponses("createdApiResponse", createdApiResponse);
        components.addResponses("badRequestApiResponse", badRequestApiResponse);
        components.addResponses("notFoundApiResponse", notFoundApiResponse);
        components.addResponses("internalServerErrorApiResponse", internalServerErrorApiResponse);
      //  components.addResponses("conflictApiResponse", conflictApiResponse);
        
        
        
        return new OpenAPI()
                            .components(components)
                            .info(new Info().title("Cuentas Bancarias")
                            .version("v1")
                            .description("Esta api nos muestra la información de las cuentas bancarias de nuestros clientes.")
                            .termsOfService("http://www.sistemasactivos.com/terminos")
                            .contact(new Contact()
                            .name("Contacto")
                            .email("contactocapacitacion@gmail.com")
                            .url("http://www.sistemasactivos.com/contacto"))
                );
        
    }
}
