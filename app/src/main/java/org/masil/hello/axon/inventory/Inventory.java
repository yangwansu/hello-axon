package org.masil.hello.axon.inventory;


import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor // TODO  없으면 어떤 오류가 나오나 -> 잘됨...
public class Inventory {
    @AggregateIdentifier
    private UUID id;
    private int quantity;

    @CommandHandler
    private Inventory(Commands.CreateInventoryCommand command){
        //TODO 여기에서 id 를 셋팅하는것은 안되나?
        AggregateLifecycle.apply(new Events.InventoryCreatedEvent(command.getInventoryId()));
        //TODO metadata 를 넘기는데 머지?
    }

    @CommandHandler
    private void handle(Commands.IncreaseInventoryCommand command) {
        AggregateLifecycle.apply(new Events.InventoryIncreasedEvent(command.getInventoryId(), command.getQuantity()));
    }

    @CommandHandler
    private void handle(Commands.DecreaseInventoryCommand command) {
        AggregateLifecycle.apply(new Events.InventoryDecreasedEvent(command.getInventoryId(), command.getQuantity()));
    }

    @EventSourcingHandler // TODO handle 이 public 이 아니면?
    private void on(Events.InventoryCreatedEvent event){
        this.id = event.getInventoryId();
    }

    @EventSourcingHandler
    private void on(Events.InventoryIncreasedEvent event) {
        // TODO lock?
        this.quantity += event.getQuantity();
    }

    @EventSourcingHandler
    private void on(Events.InventoryDecreasedEvent event) {
        this.quantity -= event.getQuantity();
    }




}
