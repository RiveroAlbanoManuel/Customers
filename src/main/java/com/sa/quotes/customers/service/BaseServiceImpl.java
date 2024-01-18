/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sa.quotes.customers.service;

import com.sa.quotes.customers.interfaces.BaseService;
import com.sa.quotes.customers.model.Base;
import com.sa.quotes.customers.model.Customer;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

import com.sa.quotes.customers.repository.BaseRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




public abstract class BaseServiceImpl <E extends Base, ID extends Serializable> implements BaseService <E,ID>{  
                                                                                                                  
    
    @Autowired
    protected BaseRepository<E, ID > baseRepository;
    
    
    
    public BaseServiceImpl (BaseRepository<E, ID > baseRepository){
        this.baseRepository = baseRepository;
    }
    
    
    
    @Transactional
    @Override
    public E save(E entity) throws Exception {   // Tuve que adaptar el save porque sino no me funcionaba el update. 
                                                    // Tiraba bad request porque detectaba que 
                                                   // tenía el id.
        try {
            if (entity.getId() == null) {
                return baseRepository.save(entity);
            } else {
                // Save new entity
                return baseRepository.save(entity);
            }  
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    
    /*
    @Override
    public E save(E entity) throws Exception {                                    
        try {
            if(entity.getId() == null){ 
                entity=baseRepository.save(entity);  // Esto lo guarda en la DB
               return entity;
            }else{
               throw new BadRequestException();
            }
          } 
           catch (Exception e){
               throw new Exception (e.getMessage());
          }                                                
    }
    
    */ // fin 
    
    
    @Transactional
    @Override
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id); 
          
          E appo = entityOptional.get(); 
            if(entity.getId() != null){
                if(entity.getId()==id){
                    if(baseRepository.existsById(id)){
                        appo = baseRepository.save(entity);
                        return appo;
                    }
                    else{
                    throw new NotFoundException (); 
                    }   
                }
                else{
                    throw new BadRequestException();
                    }
            } // If Principal
            else{
            throw new BadRequestException();
            }
        }// Try
        catch (Exception e){
        throw new Exception (e.getMessage());
        }  
    }
  
    
    
    public List<E> findAll() throws Exception {
        try{
            
            List<E> entities = baseRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception (e.getMessage());
        }
    }
    
    @Override
    public Page<E> findAll(Pageable pageable) throws Exception {

        try {

            Page<E> entities = baseRepository.findAll(pageable);
            return entities;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    

    @Override
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        }
        catch(Exception e){
            throw new Exception (e.getMessage());
        }
    }
    
    @Transactional
    @Override
    public boolean delete(ID id) throws Exception {
        try {          
            if(baseRepository.existsById(id)){
            baseRepository.deleteById(id);
            return true;
            }else {
            return false;
            }
        }
        catch (Exception e){
            throw new Exception (e.getMessage());
        }
    }
    
}   // Fin BaseServiceImpl
