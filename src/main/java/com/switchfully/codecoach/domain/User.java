package com.switchfully.codecoach.domain;

import com.switchfully.codecoach.security.authentication.user.api.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Account {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "firstname")
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

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "coach_info_id")
    private CoachInfo coachInfo;

    @Override
    public List<UserRole> getAuthorities() {
        return List.of(userRole);
    }

    @Override
    public boolean isAccountEnabled() {
        return true;
    }

    @Override
    public void enableAccount() {
    }
}
