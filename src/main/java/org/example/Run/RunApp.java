package org.example.Run;

import lombok.Getter;
import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.FestivalGate.FestivalGate;
import org.example.FestivalStatisticsThread.FestivalStatisticsThread;
import org.example.TicketType.TicketType;

import java.util.*;
import java.util.logging.Logger;

@Getter
public class RunApp {
  private FestivalGate gate;
  private FestivalGate festivalGate = new FestivalGate(TicketType.TicketTypes.FULL, gate);
  private FestivalAttendeeThread attendeeThread =
      new FestivalAttendeeThread(TicketType.TicketTypes.FULL, gate);
  private List<FestivalAttendeeThread> festivalAttendeeThreadList = new ArrayList<>();
  private String yesOrNo;
  private boolean isEmpty = true;
  private Scanner scanner = new Scanner(System.in);
  private Queue<String> finalQueueAttendeeList = new LinkedList<>();
  private static final Logger log = Logger.getLogger(RunApp.class.getName());
  private boolean running = true;

  public void run() {
    log.info("The application start!");
    festivalAttendeeThreadList = attendeeThread.addTicketTypes();

    if (running) {
      if (festivalGate.listOfAttendeesIsValid(festivalAttendeeThreadList)) {
        log.info("The list of attendees is valid!");
        isEmpty = false;
      } else {
        log.warning("The number of attendees u entered is 0, try again!");
        RunApp runApp = new RunApp();
        runApp.run();
        running = false;
      }
    }
    if (running) {
      System.out.println("Wanna see each thread and any attendee ticketType?");
      yesOrNo = scanner.nextLine();
      if (yesOrNo.equalsIgnoreCase("yes")) {
        for (int i = 1; i <= attendeeThread.getAttendeeThreads().size(); i++) {
          Thread thread = new Thread(attendeeThread);
          thread.start();
          try {
            Thread.sleep(5000);
            thread.join();
          } catch (InterruptedException e) {
            System.out.println(e.getMessage());
          }
        }
      }

      System.out.println("Wanna see statisticsThread?");
      yesOrNo = scanner.nextLine();
      if (yesOrNo.equalsIgnoreCase("yes")) {
        if (!isEmpty) {
          FestivalStatisticsThread statisticsThread =
              new FestivalStatisticsThread(gate, festivalAttendeeThreadList);
          statisticsThread.start();
          try {
            statisticsThread.join();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }

      System.out.println("Wanna remove in queue?");
      yesOrNo = scanner.nextLine();
      if (yesOrNo.equalsIgnoreCase("yes")) {
        finalQueueAttendeeList = attendeeThread.removeTicketTypes(festivalAttendeeThreadList);
        if (!finalQueueAttendeeList.isEmpty()) {
          System.out.println("Remained tickets after the removal from queue:");
          finalQueueAttendeeList.stream().forEach(ticketType -> System.out.println(ticketType));
        } else log.info("No ticketTypes remained after the removal!");
      }
    }
  }
}
