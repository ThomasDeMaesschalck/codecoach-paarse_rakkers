package com.switchfully.codecoach.api.dto;

public class SessionDTO {

    @NotBlank(message = "Coachee can not be blank") @NotNull(message = "Coachee can not be null.")
    private String coacheeId;

    @NotBlank(message = "Coach can not be blank") @NotNull(message = "Coach can not be null.")
    private String coachId;

    @Future(message = "Session can not be in the past")
    private LocalDateTime moment;

    @NotNull(message = "Face-to-face can not be null.")
    private boolean faceToFace;

    @NotBlank(message = "Remarks can not be blank") @NotNull(message = "Remarks can not be null.")
    private String remarks;


    private SessionStatus status;

    @Valid
    private CoachFeedbackDTO coachFeedback;

    @Valid
    private CoacheeFeedbackDTO coacheeFeedback;
}
