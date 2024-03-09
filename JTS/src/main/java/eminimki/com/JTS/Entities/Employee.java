package eminimki.com.JTS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "employees")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", unique = true , nullable = false)
    private String username ;

    @Column(name = "name")
    private String name ;

    @Column(name = "lastName")
    private String lastname ;

    @Column(name = "mail", unique = true, nullable = false)
    private String mail ;

    @Column(name = "password")
    private String password ;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "receiverId")
    private Set<Task> tasks;
}
