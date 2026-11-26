package org.example.FestivalAttendeeThread;

import lombok.Getter;
import lombok.Setter;
import org.example.FestivalGate.FestivalGate;
import org.example.TicketType.TicketType;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

@Getter
@Setter
public class FestivalAttendeeThread implements Runnable {

  private TicketType.TicketTypes ticketType;
  private FestivalGate gate;
  private List<FestivalAttendeeThread> attendeeThreads = new ArrayList<>();
  private Queue<TicketType.TicketTypes> ticketTypesQueued = new LinkedList<>();
  private Queue<String> finalTicketTypesQueued = new LinkedList<String>();
  private int numberOfAttendees;
  private String ticketTypeToRemove;
  private Scanner sc;
  private int numberOfTicketsToDelete;
  private static final Logger logger = Logger.getLogger(FestivalAttendeeThread.class.getName());

  public FestivalAttendeeThread(TicketType.TicketTypes ticketType, FestivalGate gate) {
    this.ticketType = ticketType;
    this.gate = gate;
  }

  private Scanner reader() {
    if (sc == null) {
      sc = new Scanner(System.in);
      return sc;
    } else {
      return sc;
    }
  }

  public List<FestivalAttendeeThread> addTicketTypes() {
    System.out.println("how many participants u wanna add to the festival?");
    sc = reader();
    numberOfAttendees = sc.nextInt();
    sc.nextLine();
    if (numberOfAttendees > 0) {
      for (int i = 1; i <= numberOfAttendees; i++) {
        TicketType.TicketTypes randomTicketTypes =
            TicketType.TicketTypes.values()[
                ThreadLocalRandom.current().nextInt(TicketType.TicketTypes.values().length)];
        FestivalAttendeeThread attendee = new FestivalAttendeeThread(randomTicketTypes, gate);
        attendee.ticketType = randomTicketTypes;
        attendee.gate = gate;
        ticketTypesQueued.add(randomTicketTypes);
        attendeeThreads.add(attendee);
      }
    } else {
      return attendeeThreads;
    }
    return attendeeThreads;
  }

  public Queue<String> removeTicketTypes(List<FestivalAttendeeThread> festivalAttendeeThreadList) {
    System.out.println("How many ticketTypes u want to delete?");
    numberOfTicketsToDelete = sc.nextInt();
    sc.nextLine();
    while (numberOfTicketsToDelete > 0) {
      System.out.println("Enter the type of ticket u want to delete!(eg: FREE_PASS)");
      ticketTypeToRemove = sc.nextLine();
      if (String.valueOf(ticketTypesQueued).contains(ticketTypeToRemove)) {
        try {
          TicketType.TicketTypes ticketTypes = TicketType.TicketTypes.valueOf(ticketTypeToRemove);
          if (ticketTypesQueued.remove(ticketTypes)) {
            logger.info("The ticketType has been successfully removed!");
            System.out.println();
          } else {
            logger.warning("No attendee has this type of ticket!");
          }
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
        }
        numberOfTicketsToDelete--;
      } else {
        System.out.println("U entered a wrong ticketType : " + ticketTypeToRemove);
        logger.severe("Please enter an valid ticketType!");
      }
    }
    for (TicketType.TicketTypes ticket : ticketTypesQueued) {
      finalTicketTypesQueued.add(String.valueOf(ticket));
    }
    return finalTicketTypesQueued;
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    String[] expressionByParts = threadName.split("-");
    int index = Integer.parseInt(expressionByParts[1]);
    System.out.println(
        "Attendee -> "
            + expressionByParts[1]
            + " with ticketType: "
            + attendeeThreads.get(index).getTicketType());
  }
}
