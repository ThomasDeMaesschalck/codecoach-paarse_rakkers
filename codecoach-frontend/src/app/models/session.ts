import {CoachFeedback} from "./coachfeedback";
import {CoacheeFeedback} from "./coacheefeedback";
import {SessionStatus} from "./sessionstatus";

export interface Session {

  id: string;

  subject: string;

  coacheeId: string;

  coachId: string;

  moment: Date;

  faceToFace: boolean;

  remarks: string;

  status: SessionStatus;

  coachFeedback: CoachFeedback;

  coacheeFeedback: CoacheeFeedback;

}
