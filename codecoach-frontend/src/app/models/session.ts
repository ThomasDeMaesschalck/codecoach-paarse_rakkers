import {CoachFeedback} from "./coachfeedback";
import {CoacheeFeedback} from "./coacheefeedback";

export interface Session {

  sessionId: string;

  coacheeId: string;

  coachId: string;

  moment: Date;

  faceToFace: boolean;

  remarks: string;

  status: SessionStatus;

  coachFeedback: CoachFeedback;

  coacheeFeedback: CoacheeFeedback;

}
