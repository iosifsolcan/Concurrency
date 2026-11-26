package org.example.FestivalStatisticsThread;

import lombok.Getter;
import org.example.FestivalAttendeeThread.FestivalAttendeeThread;
import org.example.FestivalGate.FestivalGate;

import java.util.List;

@Getter
public class FestivalStatisticsThread extends Thread {
  private FestivalGate gate;
  private List<FestivalAttendeeThread> attendeeThreadList;
  private int sumOfFullVipTicket = 0;
  private int sumOfOneDayTicket = 0;
  private int sumOfFreePassTicket = 0;
  private int sumOfFullTicket = 0;
  private int sumOfOneDayVipTicket = 0;

  public FestivalStatisticsThread(
      FestivalGate gate, List<FestivalAttendeeThread> attendeeThreadList) {
    this.gate = gate;
    this.attendeeThreadList = attendeeThreadList;
  }

  @Override
  public void run() {
    for (FestivalAttendeeThread attendeeThread1 : attendeeThreadList) {
      switch (attendeeThread1.getTicketType()) {
        case FULL -> sumOfFullTicket++;
        case FULL_VIP -> sumOfFullVipTicket++;
        case FREE_PASS -> sumOfFreePassTicket++;
        case ONE_DAY -> sumOfOneDayTicket++;
        case ONE_DAY_VIP -> sumOfOneDayVipTicket++;
      }
    }
    try {
      System.out.println("People with FULL tickets: " + sumOfFullTicket);
      Thread.sleep(1000);
      System.out.println("People with FULL VIP tickets: " + sumOfFullVipTicket);
      Thread.sleep(1000);
      System.out.println("People with FREE PASS tickets: " + sumOfFreePassTicket);
      Thread.sleep(1000);
      System.out.println("People with ONE DAY tickets: " + sumOfOneDayTicket);
      Thread.sleep(1000);
      System.out.println("People with ONE DAY VIP tickets: " + sumOfOneDayVipTicket);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
