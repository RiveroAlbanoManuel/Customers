/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sa.quotes.customers.repository;

import com.sa.quotes.customers.model.Customer;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author manuel
 */


@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer>{
        //jpa
    List<Customer> findByFirstnameContainingOrEmailContaining(String firstname, String email);
   
    
    
    
     /*
                NOTESE QUE CUANDO ES NATIVEQUERY NO VA EL NOMBRE DE LA ENTIDAD, SINO EL DE LA BASE DE DATOS.
                SI PONEMOS EL DE LA ENTIDAD TIRA ERROR EL POSTMAN
    */
    //jpql 
    @Query( value = "SELECT a FROM Customer a WHERE a.firstname LIKE %:filter% OR a.email LIKE %:filter%" )
    List<Customer> search(@Param ("filter") String filter);
    
    //native query
    @Query(nativeQuery = true, value = "SELECT * FROM customers a WHERE a.firstname LIKE %:filter% OR a.email LIKE %:filter%"  )
    List<Customer> searchNative(@Param ("filter") String filter);
    
                    
    Page <Customer> findByFirstnameContainingOrEmailContaining(String firstname, String email, Pageable pageable);
    
    @Query( 
            value = "SELECT a FROM Customer a WHERE a.firstname LIKE %:filter% OR a.email LIKE %:filter%" )
            
    Page<Customer> search(@Param ("filter") String filter, Pageable pageable);
    
    
    @Query(
            nativeQuery = true, 
            value = "SELECT * FROM customers a WHERE a.firstname LIKE %:filter% OR a.email LIKE %:filter%"  )
            
    Page<Customer> searchNative(@Param ("filter") String filter, Pageable pageable);
    
}
