package com.Commercial.commercial.DAO;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "client")
@Entity
public class client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @NotEmpty(message = "name field is empty")
    private String name;

    @Column(unique = true)
    @Email(message = "email format is not valid")
    @NotEmpty(message = "email field is empty")
    private String email;

    @Column
    @NotEmpty(message = "phone field is empty")
    private String phone;
    @Column
    @NotEmpty(message = "address field is empty")
    private String address;


}

