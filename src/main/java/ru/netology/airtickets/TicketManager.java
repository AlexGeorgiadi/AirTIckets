package ru.netology.airtickets;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public Ticket[] searchBy(String text) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (matches(ticket, text)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String search) {
        if (ticket.getFrom().contains(search)) {
            return true;
        } else if (ticket.getTo().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}