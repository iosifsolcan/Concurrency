package org.example.Run;

import lombok.Data;
import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.FestivalGate.FestivalGate;
import org.example.FestivalStatisticsThread.FestivalStatisticsThread;
import org.example.TicketType.TicketType;

import java.util.*;
import java.util.logging.Logger;

@Data
public class RunApp {
  private FestivalGate gate;
  private FestivalGate festivalGate = new FestivalGate(TicketType.TicketTypes.FULL, gate);
  private FestivalAttendeeThread attendeeThread =
      new FestivalAttendeeThread(TicketType.TicketTypes.FULL, gate);
  private List<FestivalAttendeeThread> festivalAttendeeThreadList = new ArrayList<>();
  private String yesOrNo;
  private boolean isEmpty = true;
  private Scanner scanner = new Scanner(System.in);
  private List<FestivalAttendeeThread> finalAttendeeList = new LinkedList<>();
  private static final Logger log = Logger.getLogger(RunApp.class.getName());
  private boolean running = true;

  public void run() {

    if (festivalGate.ticketTypesNumberIsValid()) {
      System.out.println("TicketTypes number is correct!");
      festivalAttendeeThreadList = attendeeThread.addTicketTypes();
    } else {
      System.out.println("TicketTypes number is incorrect! The application can't work like that!!");
      log.severe("The application will be stopped!");
      running = false;
    }

    if (running) {
      if (festivalGate.listOfAttendeesIsValid()) {
        System.out.println("The list of attendees is valid! ");
        isEmpty = false;
      } else {
        System.out.println("The list of attendees is not valid! ");
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
            thread.sleep(5000);
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
        finalAttendeeList = attendeeThread.removeTicketTypes(festivalAttendeeThreadList);
        System.out.println("Remained tickets after the removal from queue:");
        festivalAttendeeThreadList.stream()
            .forEach(attendee -> System.out.println(attendee.getTicketType()));
      }
    }
  }
}
