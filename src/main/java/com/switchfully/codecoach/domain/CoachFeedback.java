package com.switchfully.codecoach.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coach_feedback")
public class CoachFeedback {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "clarity")
    private int clarity;
    @Column(name = "usefulness")
    private int usefulness;
    @Column(name = "positives")
    private String positives;
    @Column(name = "working_points")
    private String workingPoints;


}
