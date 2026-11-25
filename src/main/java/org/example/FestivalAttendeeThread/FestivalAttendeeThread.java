package org.example.FestivalAttendeeThread;

import lombok.Data;
import org.example.FestivalGate.FestivalGate;
import org.example.TicketType.TicketType;

import java.util.*;
import java.util.logging.Logger;

@Data
public class FestivalAttendeeThread implements Runnable {

  private TicketType.TicketTypes ticketType;
  private FestivalGate gate;
  private List<FestivalAttendeeThread> attendeeThreads = new ArrayList<>();
  private Queue<TicketType.TicketTypes> ticketTypesQueued = new LinkedList<>();
  private int numberOfAttendees;
  private String ticketTypeToRemove;
  private Scanner sc = new Scanner(System.in);
  private int numberOfTicketsToDelete;
  private static final Logger logger = Logger.getLogger(FestivalAttendeeThread.class.getName());

  public FestivalAttendeeThread(TicketType.TicketTypes ticketType, FestivalGate gate) {
    this.ticketType = ticketType;
    this.gate = gate;
  }

  public List<FestivalAttendeeThread> addTicketTypes() {
    System.out.println("how many participants u wanna add to the festival?");
    numberOfAttendees = sc.nextInt();
    sc.nextLine();
    for (int i = 1; i <= numberOfAttendees; i++) {
      TicketType.TicketTypes randomTicketTypes =
          TicketType.TicketTypes.values()[
              (int) (Math.random() * TicketType.TicketTypes.values().length)];
      FestivalAttendeeThread attendee = new FestivalAttendeeThread(randomTicketTypes, gate);
      attendee.ticketType = randomTicketTypes;
      attendee.gate = gate;
      ticketTypesQueued.add(randomTicketTypes);
      attendeeThreads.add(attendee);
    }
    return attendeeThreads;
  }

  public List<FestivalAttendeeThread> removeTicketTypes(
      List<FestivalAttendeeThread> festivalAttendeeThreadList) {
    System.out.println("How many tickets u want to delete?");
    numberOfTicketsToDelete = sc.nextInt();
    sc.nextLine();
    while (numberOfTicketsToDelete > 0) {
      System.out.println("Enter the type of ticket u want to delete!(eg: FREE_PASS)");
      ticketTypeToRemove = sc.nextLine();
      if (String.valueOf(ticketTypesQueued).contains(ticketTypeToRemove)) {
        try {
          TicketType.TicketTypes ticketTypes = TicketType.TicketTypes.valueOf(ticketTypeToRemove);
          if (ticketTypesQueued.remove(ticketTypes)) {
            System.out.println("ticket removed successfully!");
            festivalAttendeeThreadList.removeIf(
                attendee -> attendee.getTicketType().equals(ticketTypes));
            logger.info("The ticketType has been successfully removed!");
            System.out.println();
          } else {
            System.out.println("Ticket type not found in Queue!");
            logger.warning("No attendee has this type of ticket!");
          }
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
        }
        numberOfTicketsToDelete--;
      } else {
        System.out.println("U entered a wrong ticketType!");
        logger.severe("Please enter an valid ticketType!");
      }
    }
    return festivalAttendeeThreadList;
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    String[] expressionByParts = threadName.split("-");
    int index = Integer.parseInt(expressionByParts[1]);
    System.out.println(
        threadName + " on attendee with ticketType: " + attendeeThreads.get(index).getTicketType());
  }
}
