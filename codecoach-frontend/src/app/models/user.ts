import {CoachInfo} from "./coachinfo";

export interface User {

  id: string
  firstName : string;
  lastName: string;
  email: string;
  password: string;
  pictureURL: string;
  companyTeam: string;
  userRole: UserRole;
  coachInfo: CoachInfo;
}
