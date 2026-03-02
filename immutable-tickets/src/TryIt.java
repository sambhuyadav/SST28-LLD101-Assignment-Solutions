import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {
public static void main(String[] args) {
    TicketService service = new TicketService();

    IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
    System.out.println("Created: " + t);

    // Demonstrate post-creation "mutation" via new instance
    t = service.assign(t, "agent@example.com");
    t = service.escalateToCritical(t);
    System.out.println("\nAfter service updates (new instance): " + t);

    // Demonstrate external mutation attempt (should not affect ticket)
    List<String> tags = new ArrayList<>(t.getTags());
    tags.add("HACKED_FROM_OUTSIDE");
    System.out.println("\nAfter external tag mutation attempt: " + t);

    // The ticket's tags remain unchanged
}
}
