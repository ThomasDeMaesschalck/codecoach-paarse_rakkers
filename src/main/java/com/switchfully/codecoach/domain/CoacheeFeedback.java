package com.switchfully.codecoach.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoacheeFeedback {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "preparedness")
    private int preparedness;

    @Column(name = "willingness")
    private int willingness;

    @Column(name = "positives")
    private String positives;

    @Column(name = "working_points")
    private String workingPoints;
}
