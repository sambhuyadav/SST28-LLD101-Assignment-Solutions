package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop
 * mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return IncidentTicket.builder()
                .id(t.getId())
                .reporterEmail(t.getReporterEmail())
                .title(t.getTitle())
                .description(t.getDescription())
                .priority("CRITICAL")
                .tags(t.getTags())
                .assigneeEmail(t.getAssigneeEmail())
                .customerVisible(t.isCustomerVisible())
                .slaMinutes(t.getSlaMinutes())
                .source(t.getSource())
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return IncidentTicket.builder()
                .id(t.getId())
                .reporterEmail(t.getReporterEmail())
                .title(t.getTitle())
                .description(t.getDescription())
                .priority(t.getPriority())
                .tags(t.getTags())
                .assigneeEmail(assigneeEmail)
                .customerVisible(t.isCustomerVisible())
                .slaMinutes(t.getSlaMinutes())
                .source(t.getSource())
                .build();
    }
}