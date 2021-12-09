package com.switchfully.codecoach.domain;

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
@Table(name = "coach_info")
public class CoachInfo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "availability")
    private String availability;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coach_info_id")
    private List<Topic> topics;
}
