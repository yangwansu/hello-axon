package org.masil.inventory;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateRoot;

import java.util.UUID;

@AggregateRoot
@NoArgsConstructor
public class Inventory {

    @AggregateIdentifier
    private UUID id;
    private int quantity;


    @CommandHandler
    public Inventory(Commands.CreateInventoryCommand command) {
        AggregateLifecycle.apply(new Events.InventoryCreatedEvent(command.getInventoryId()));
    }

    @CommandHandler
    public void handle(Commands.IncreaseInventoryCommand command) {
        this.quantity += command.getDelta();
        AggregateLifecycle.apply(new Events.InventoryIncreasedEvent(command.getInventoryId(), command.getDelta(), this.quantity));
    }

    @CommandHandler
    public void handle(Commands.DecreaseInventoryCommand command) {
        this.quantity -= command.getDelta();
        AggregateLifecycle.apply(new Events.InventoryDecreasedEvent(command.getInventoryId(), command.getDelta(), this.quantity));
    }


    @EventSourcingHandler
    public void on(Events.InventoryCreatedEvent event) {
        this.id = event.getInventoryId();
        this.quantity = 0;
    }

    @EventSourcingHandler
    public void on(Events.InventoryIncreasedEvent event) {
        this.quantity = event.getRemained();
    }

    @EventSourcingHandler
    public void on(Events.InventoryDecreasedEvent event) {
        this.quantity = event.getRemained();
    }


}
