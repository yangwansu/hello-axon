package org.masil.hello.axon.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.masil.hello.axon.inventory.commands.DecreaseInventoryCommand;
import org.masil.hello.axon.inventory.commands.IncreaseInventoryCommand;
import org.masil.hello.axon.inventory.commands.InitInventoryCommand;
import org.masil.hello.axon.inventory.events.InventoryDecreasedEvent;
import org.masil.hello.axon.inventory.events.InventoryIncreasedEvent;
import org.masil.hello.axon.inventory.events.InventoryInitedEvent;

import java.util.UUID;

@Getter
@Aggregate
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @AggregateIdentifier
    private UUID id;
    private int quantity;


    @CommandHandler
    public Inventory(InitInventoryCommand command) {
        AggregateLifecycle.apply(new InventoryInitedEvent(command.getInventoryId()));
    }

    @CommandHandler
    public void handle(IncreaseInventoryCommand command) {
        AggregateLifecycle.apply(new InventoryIncreasedEvent(command.getInventoryId(), command.getQuantity()));
    }


    @CommandHandler
    public void handle(DecreaseInventoryCommand command) {
        AggregateLifecycle.apply(new InventoryDecreasedEvent(command.getInventoryId(), command.getQuantity()));
    }


    @EventSourcingHandler
    public void on(InventoryInitedEvent event) {
        this.id = event.getInventoryId();
    }

    @EventSourcingHandler
    public void on(InventoryIncreasedEvent event) {
        this.quantity += event.getQuantity();
    }

    @EventSourcingHandler
    public void on(InventoryDecreasedEvent event) {
        this.quantity -= event.getQuantity();
    }


}
