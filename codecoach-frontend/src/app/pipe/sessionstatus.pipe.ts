import {Pipe, PipeTransform} from '@angular/core';
import {SessionStatus} from "../models/sessionstatus";

@Pipe({
  name: 'sessionstatus'
})
export class SessionstatusPipe implements PipeTransform {

  transform(value: SessionStatus): string {
    switch (value.toString()) {
      case "ACCEPTED" : {
        return "Accepted";
      }
      case "CANCELED_BY_COACHEE": {

        return "Canceled (by coachee)";

      }
      case "CANCELED_BY_COACH": {
        return "Canceled (by coach)";
      }
      case "DECLINED": {
        return "Declined (by coach)";

      }
      case "DONE": {
        return "Done";

      }
      case "DONE_WAITING_FEEDBACK": {
        return "Waiting for feedback";
      }
      case "REQUESTED": {
        return "Requested";
      }
      default: return "Error";
    }
  }

}
