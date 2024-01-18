/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sa.quotes.customers.service;

import com.sa.quotes.customers.interfaces.BaseService;
import com.sa.quotes.customers.interfaces.CustomerService;
import com.sa.quotes.customers.model.Base;
import com.sa.quotes.customers.model.Customer;
import com.sa.quotes.customers.repository.BaseRepository;
import com.sa.quotes.customers.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer> implements CustomerService{
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public CustomerServiceImpl(BaseRepository<Customer, Integer> baseRepository) {
        super(baseRepository);
       
    }
        /*
    @Override
    public Page<Account> findAll(Pageable pageable) throws Exception {      ?¿?¿? -- Estaba en el proyecto de la clase de Pageable así comentado
        throw new UnsupportedOperationException("Not supported yet.");
    }
    */


    
   
    @Transactional
    @Override
    public Customer update(Integer id, Customer requestBodyCustomer) throws Exception {
        Date currentTime = new Date();
        
       
        try {
            Optional<Customer> entityOptional = baseRepository.findById(id); // Ver, si la entidad no existe, tira bad request en lugar de not found
            
             if(entityOptional==null){   
                throw new NotFoundException();
                
            }if(id != requestBodyCustomer.getId()){
                throw new BadRequestException();
            }
                 
                Customer newCustomer = entityOptional.get();
                newCustomer.setUpdatedAt(currentTime);
                
                Date fechaActual = entityOptional.get().getCreatedAt(); // Guarda createdAt de la entity actual
                newCustomer.setCreatedAt(fechaActual);
                newCustomer.setFirstname(requestBodyCustomer.getFirstname());
                newCustomer.setAge(requestBodyCustomer.getAge());
                newCustomer.setDatebirth(requestBodyCustomer.getDatebirth());
                newCustomer.setDni(requestBodyCustomer.getDni());
                newCustomer.setPhone(requestBodyCustomer.getPhone());
                newCustomer.setEmail(requestBodyCustomer.getEmail());
                
                return super.save(newCustomer);

       }catch(BadRequestException e){
           throw e;
       }catch(NotFoundException e){
           throw e;
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
} // FIN UPDATE            
   
    @Override
    public List<Customer> search (String filter) throws Exception{
        try{
            //List<Customer > entities = customerRepository.findByBancoContainingOrNombreTitularContaining(filter, filter);
            //List<Customer > entities = customerRepository.search(filter);  --------- Estaban así comentadas
            List<Customer > entities = customerRepository.searchNative(filter);
            return entities;
        }catch (Exception e){
            throw new Exception (e.getMessage());
        }
    }
    
        
    @Override //Si lo pongo se me pone todo en rojo?
    public Page<Customer> search(String filter, Pageable pageable) throws Exception {
        
        try{
            Page<Customer> entities = customerRepository.searchNative(filter,pageable); 
            return entities;
        }catch (Exception e){
            throw new Exception (e.getMessage());
        }
        
    }









}  

