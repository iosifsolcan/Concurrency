import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.TicketType.TicketType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestingFestivalAttendeeThread {

  @Test
  public void testThreadStartsAndFinishes() throws InterruptedException {
    FestivalAttendeeThread attendee = new FestivalAttendeeThread(TicketType.TicketTypes.FULL, null);
    attendee.addTicketTypes();

    for (FestivalAttendeeThread attendee1 : attendee.getAttendeeThreads()) {
      Thread thread = new Thread(attendee1);
      thread.start();
      assertTrue(thread.isAlive());
      Thread.sleep(5000);
      thread.join();
      assertFalse(thread.isAlive());
    }
  }
}
