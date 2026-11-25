package org.example.FestivalGate;

import lombok.Data;
import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.TicketType.TicketType;

@Data
public class FestivalGate extends FestivalAttendeeThread {
  private boolean isValid = false;
  private FestivalAttendeeThread attendeeThread =
      new FestivalAttendeeThread(getTicketType(), getGate());

  public FestivalGate(TicketType.TicketTypes ticketType, FestivalGate gate) {
    super(ticketType, gate);
  }

  public boolean ticketTypesNumberIsValid() {
    if (TicketType.TicketTypes.values().length != 5) {
      return isValid;
    }
    isValid = true;
    return isValid;
  }

  public boolean listOfAttendeesIsValid() {
    isValid = false;
    if (attendeeThread.getAttendeeThreads() != null) isValid = true;
    return isValid;
  }
}
