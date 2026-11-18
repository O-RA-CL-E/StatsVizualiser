package com.basespringboot.form;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Getter 
@Setter 

public class Form {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

}