import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.TicketType.TicketType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestingAddTicketTypes {
  private Scanner scanner = mock(Scanner.class);

  @Test
  public void testFestivalAttendeeThread() throws InterruptedException {
    FestivalAttendeeThread attendee = new FestivalAttendeeThread(TicketType.TicketTypes.FULL, null);
    attendee.setSc(scanner);
    when(scanner.nextInt()).thenReturn(3);
    List<FestivalAttendeeThread> attendeeThreadList = attendee.addTicketTypes();
    assertEquals(3, attendeeThreadList.size());
  }
}
