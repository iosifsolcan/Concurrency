package org.example.FestivalGate;

import lombok.Getter;
import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.TicketType.TicketType;

import java.util.List;

@Getter
public class FestivalGate extends FestivalAttendeeThread {
  private boolean isValid = false;

  public FestivalGate(TicketType.TicketTypes ticketType, FestivalGate gate) {
    super(ticketType, gate);
  }

  public boolean listOfAttendeesIsValid(List<FestivalAttendeeThread> list) {
    if (!list.isEmpty()) {
      isValid = true;
    }

    return isValid;
  }
}
