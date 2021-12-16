import {CoachFeedback} from "./coachfeedback";
import {CoacheeFeedback} from "./coacheefeedback";
import {SessionStatus} from "./sessionstatus";

export interface Session {

  id?: string;

  subject: string;

  coacheeId: string;

  coacheeName: string

  coachId: string;

  coachName: string

  moment: string;

  faceToFace: boolean;

  remarks: string;

  status?: SessionStatus;

  coachFeedback?: CoachFeedback;

  coacheeFeedback?: CoacheeFeedback;

}
