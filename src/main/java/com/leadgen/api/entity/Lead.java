package com.leadgen.api.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne(cascade = CascadeType.ALL) // or CascadeType.REMOVE, CascadeType.PERSIST, etc., based on your requirements
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;



}