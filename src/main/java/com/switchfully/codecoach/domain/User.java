package com.switchfully.codecoach.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name="firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "picture_url")
    private String pictureURL;

    @Column(name = "company_team")
    private String companyTeam;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coach_info_id")
    private CoachInfo coachInfo;

}
