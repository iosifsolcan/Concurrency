import org.example.FestivalGate.FestivalGate;
import org.example.TicketType.TicketType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTicketTypesNumberIsValid {

  @Test
  public void testTicketTypesNumberIsValid() {
    FestivalGate festivalGate = new FestivalGate(TicketType.TicketTypes.FULL, null);
    assertTrue(festivalGate.ticketTypesNumberIsValid());
  }
}
