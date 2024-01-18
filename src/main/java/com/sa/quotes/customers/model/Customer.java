package com.sa.quotes.customers.model;


import static com.zaxxer.hikari.util.ClockSource.currentTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.text.SimpleDateFormat;

//import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@AllArgsConstructor

@Getter
@Setter
@Table(name = "customers")
public class Customer extends Base{
    
    
    @NotNull(message = "dni may not be null")
    @Column
    private int dni;
    @NotNull(message = "firstname may not be null")
    @Column
    private String firstname;
    @NotNull(message = "age may not be null")
    @Column
    private int age;
    @NotNull(message = "datebirth may not be null")
    @Column
    private String datebirth;
    @NotNull(message = "email may not be null")
    @Column
    private String email;
    
    @NotNull(message = "phone may not be null")
    @Column
    private String phone;
    // Date attributes
    @Setter(onMethod_ = @GeneratedValue)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true)
    private Date createdAt;
    @Setter(onMethod_ = @GeneratedValue)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt; 
    
    public Customer(){
        if (this.createdAt == null){
            this.createdAt = new Date();  // Setea la create date al momento de la creación
        }
        if (this.updatedAt == null){
            this.updatedAt = new Date();  // Setea la updated date al momento de la creación. Solo 
                                          // cuando se crea que es null, luego en los updates, no.
        }
    }

}


