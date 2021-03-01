package org.masil.hello.axon.inventory.view.app;

import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.masil.hello.axon.inventory.Events;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class InventoryViewProjector {

    private final InventoryViewRepository repository;


    @EventHandler
    public void handle(Events.InventoryCreatedEvent event) {
        InventoryView view = new InventoryView(event.getInventoryId(), 0);
        repository.save(view);
    }

    @EventHandler
    public void handle(Events.InventoryIncreasedEvent event) {
        InventoryView view = repository.getOne(event.getInventoryId());
        InventoryView updated = new InventoryView(view.getId(), view.getQuantity() + event.getQuantity());
        repository.save(updated);
    }

    @EventHandler
    public void handle(Events.InventoryDecreasedEvent event) {
        InventoryView view = repository.getOne(event.getInventoryId());
        InventoryView updated = new InventoryView(view.getId(), view.getQuantity() - event.getQuantity());
        repository.save(updated);
    }

    @QueryHandler
    private InventoryView handle(Queries.FindInventoryViewQuery query) {
        return repository.getOne(query.getInventoryId());
    }

    @QueryHandler
    private List<InventoryView> handle(Queries.AllInventoryViewQuery query) {
        return repository.findAll();
    }

}