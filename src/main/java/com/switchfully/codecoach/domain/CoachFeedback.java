package com.switchfully.codecoach.domain;

public class CoachFeedback {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="clarity")
    private int clarity;
    @Column(name = "usefulness")
    private int usefulness;
    @Column(name="positives")
    private String positives;
    @Column(name="working_points")
    private String workingPoints;







}
