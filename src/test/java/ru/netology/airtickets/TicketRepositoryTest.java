package ru.netology.airtickets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.*;

public class TicketRepositoryTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(1, 10000, "MRV", "ATH", 120);
    Ticket ticket2 = new Ticket(2, 9000, "MRV", "SKG", 100);
    Ticket ticket3 = new Ticket(3, 10000, "MRV", "LCA", 130);
    Ticket ticket4 = new Ticket(4, 8000, "KUT", "ATH", 90);
    Ticket ticket5 = new Ticket(5, 4000, "BUS", "SKG", 60);

    @BeforeEach
    public void save() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
    }

    @Test
    public void shouldFindAll() {
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemove() {
        repo.removeById(1);

        Ticket[] expected = new Ticket[]{ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFind2() {
        Ticket[] expected = new Ticket[]{ticket1, ticket4};
        Ticket[] actual = manager.searchBy("ATH");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFind1() {
        Ticket[] expected = new Ticket[]{ticket3};
        Ticket[] actual = manager.searchBy("LCA");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAnyTickets() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("SVO");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveAbsentId() {

        Assertions.assertThrows(NotFoundException.class, () ->
        {
            repo.removeById(100);
        });
    }

    @Test
    public void shouldFindAndSort() {
        Ticket[] expected = new Ticket[]{ticket2, ticket1, ticket3};
        Ticket[] actual = manager.searchBy("MRV");
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

}
