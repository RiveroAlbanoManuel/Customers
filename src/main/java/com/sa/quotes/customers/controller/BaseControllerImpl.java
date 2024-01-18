/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sa.quotes.customers.controller;

import com.sa.quotes.customers.interfaces.BaseController;
import com.sa.quotes.customers.model.Base;

import com.sa.quotes.customers.service.BaseServiceImpl;
import com.sa.quotes.customers.service.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;

/**
 *
 * @author manuel
 */
public abstract class BaseControllerImpl <E extends Base,
                                          S extends BaseServiceImpl<E, Integer>>
                                          implements BaseController<E, Integer> {
    @Autowired
    public S service;
    
    @Override
    @GetMapping("")

    @Operation(
        description = "Obtener todos los registros",
        responses = {
            @ApiResponse(responseCode = "200", ref = "okAPI"),
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"), // Not found si la base de datos del path no existe.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
        }
    )
    
    public ResponseEntity<?> getAllRecord(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
    
    
    @Operation(
        description = "Obtener todos los registros, con paginado o sin paginado",
        responses = {
            @ApiResponse(responseCode = "200", ref = "okAPI"),
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"), // Not found si la base de datos del path no existe.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
        }
    )
    
    @Override
    @GetMapping("/paged")
    public ResponseEntity<?> getAllRecord(Pageable pageable) {

        try {

            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron registros");

        }
    }
    

    @GetMapping("/{id}")
    @Override
    
    @Operation(
        description = "Obtener un registro",
        responses = {
            @ApiResponse(responseCode = "200", ref = "okAPI"),
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"),
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
        }
    )
    
        public ResponseEntity<?> getRecordById(@PathVariable Integer id) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
        
        @Transactional
        @PostMapping("")
        @Override
        
        @Operation(
        description = "Guardar registro",
        responses = {
            @ApiResponse(responseCode = "201", ref = "createdApiResponse"),//Debe devolver created si se realizó la operación correctamente.
            @ApiResponse(responseCode = "400", ref = "badRequestApiResponse"),//Debe devolver badRequest si el body no es correcto.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
            
                
        }
    )
        
        public ResponseEntity<?> save(@RequestBody E entity) {
            
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
            }
            catch(BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        
        
        
        @Transactional
        @PutMapping("/{id}")
        @Override
        
        
        @Operation(
        description = "Actualizar registsro",
        responses = {
            @ApiResponse(responseCode = "200", ref = "okAPI"), // OK si los ID existen y son iguales.
            @ApiResponse(responseCode = "400", ref = "badRequestApiResponse"),// BAD_REQUEST si: No existen los dos ID, o si existen y son diferentes.
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"), // NOT_FOUND si los ID coinciden pero no están en la base.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse"),// INTERNAL_SERVER_ERROR si hay Error del servidor.
           // @ApiResponse(responseCode = "409", ref = "conflictApiResponse")// CONFLICT si hay está repetido.
        }
    )
        
        public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody E entity) {
            try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
            }
            catch(HttpServerErrorException.InternalServerError e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            catch(BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Tira éste error no sé por qué.  Tira el catch default si del customerservice
                                                                            // le tiro super.update(id,bodyrequest)
            }
        }
        
        
        
        
        @DeleteMapping("/{id}")
        @Override
        
        @Operation(
        description = "Eliminar registro",
        responses = {
            @ApiResponse(responseCode = "204", ref = "noContentApiResponse"), // 204 si se eliminó.
            @ApiResponse(responseCode = "404", ref = "notFoundApiResponse"), // 404 si no existe el id.
            @ApiResponse(responseCode = "500", ref = "internalServerErrorApiResponse")// INTERNAL_SERVER_ERROR si hay Error del servidor.
                
        }
    )
       
        public ResponseEntity<?> delete(@PathVariable Integer id) {
            try {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
            }
        }
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
