package com.akzam.postgreSqlCrud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "second_phone_number")
    private String secondPhoneNumber;

    @CreatedDate
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
