
package com.sa.quotes.customers.controller;

import com.sa.quotes.customers.model.Customer;
import com.sa.quotes.customers.service.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api/v1/customers")

public class CustomerController extends BaseControllerImpl<Customer, CustomerServiceImpl>{

    @Autowired
    private CustomerServiceImpl customerService;
    
    
    
        @GetMapping("/search")
        @Operation(
            description = "Obtener un registro filtrando por nombre o email",
            responses = {
            @ApiResponse(responseCode = "200", ref = "okAPI"),
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"), // Not found si la base de datos del path no existe.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
        }
    )
    public ResponseEntity<?> search (@RequestParam String filter){
        try{
            
            return ResponseEntity.status(HttpStatus.OK).body(customerService.search(filter));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/searchPaged")
    @Operation(
            description = "Obtener una página de registros filtrando por nombre o email",
            responses = {
            @ApiResponse(responseCode = "200", ref = "okAPI"),
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"), // Not found si la base de datos del path no existe.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
        }
    )
   public ResponseEntity<?> search (@RequestParam String filter, Pageable pageable){
       try{
           return ResponseEntity.status(HttpStatus.OK).body(customerService.search(filter, pageable));
      }catch (Exception e){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }

}


