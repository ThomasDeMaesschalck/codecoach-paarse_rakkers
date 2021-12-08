package com.switchfully.codecoach.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "coachee_id")
    private User coachee;
    @OneToOne
    @JoinColumn(name = "coach_id")
    private User coach;

    @Column(name = "moment")
    private LocalDateTime moment;

    @Column(name = "face_to_face")
    private boolean faceToFace;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SessionStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coach_feedback")
    private CoachFeedback coachFeedback;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coachee_feedback")
    private CoacheeFeedback coacheeFeedback;

}
