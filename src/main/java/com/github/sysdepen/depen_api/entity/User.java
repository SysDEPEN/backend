package com.github.sysdepen.depen_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    @NotBlank
    private String name;

    @Column(length = 14)
    @Pattern(
            regexp = "([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}|[0-9]{11})|([a-zA-Z0-9]{1,12})",
            message = "Documento inválido: forneça um CPF válido (com ou sem pontuação) ou um RNE válido"
    )
    private String document;

    @Column(length = 254)
    private String gender;

    @PastOrPresent(message = "não deixe em branco")
    private Date data_birth;

    @Column(length = 256)
    @Email
    private String email;

    @Column(nullable = false, length = 256)
    @NotBlank
    private String password;

    @Column(nullable = false)
    private Short role;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<Protocols> protocols;

    @OneToOne
    @JsonIgnoreProperties
    private Address address;

    @OneToOne
    @JsonIgnoreProperties
    private Documents doc;
}
