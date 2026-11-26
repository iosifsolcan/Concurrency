import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.TicketType.TicketType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestingRemoveTicketTypes {
  private Scanner scanner = mock(Scanner.class);

  @Test
  public void testingRemoveTicketTypes() {
    FestivalAttendeeThread attendee = new FestivalAttendeeThread(TicketType.TicketTypes.FULL, null);
    attendee.setSc(scanner);
    when(scanner.nextInt()).thenReturn(5, 1);
    when(scanner.nextLine()).thenReturn("FREE_PASS");
    List<FestivalAttendeeThread> attendeeThreadList = attendee.addTicketTypes();
    Queue<String> festivalAttendeeThreads = attendee.removeTicketTypes(attendeeThreadList);
    assertTrue(festivalAttendeeThreads.size() <= attendeeThreadList.size());
  }
}
