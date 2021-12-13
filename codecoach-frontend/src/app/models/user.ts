import {CoachInfo} from "./coachinfo";
import {UserRole} from "./userrole";

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
