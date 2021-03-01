package org.masil.inventory.aggregate;


import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.masil.inventory.commands.Commands;
import org.masil.inventory.events.Events;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class Inventory {


    @AggregateIdentifier
    private UUID id;
    private int quantity;

    @CommandHandler
    private Inventory(Commands.CreateInventoryCommand command) {
        AggregateLifecycle.apply(new Events.InventoryCreatedEvent(command.getInventoryId()));
    }

    @CommandHandler
    private void handle(Commands.IncreaseInventoryCommand command) {
        AggregateLifecycle.apply(new Events.InventoryIncreasedEvent(command.getInventoryId(), command.getQuantity()));
    }

    @CommandHandler
    private void handle(Commands.DecreaseInventoryCommand command) {
        AggregateLifecycle.apply(new Events.InventoryDecreasedEvent(command.getInventoryId(), command.getQuantity()));
    }

    @EventSourcingHandler
    private void on(Events.InventoryCreatedEvent event) {
        this.id = event.getInventoryId();
    }

    @EventSourcingHandler
    private void on(Events.InventoryIncreasedEvent event) {
        this.quantity += event.getQuantity();
    }

    @EventSourcingHandler
    private void on(Events.InventoryDecreasedEvent event) {
        this.quantity -= event.getQuantity();
    }

}
