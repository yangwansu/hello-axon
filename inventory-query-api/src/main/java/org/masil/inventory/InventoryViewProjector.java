package org.masil.inventory;


import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class InventoryViewProjector {

    private final InventoryViewRepository repository;


    @EventHandler
    public void on(Events.InventoryCreatedEvent event) {
        repository.save(new InventoryView(event.getInventoryId(), 0));
    }

    @EventHandler
    public void on(Events.InventoryIncreasedEvent event) {
        InventoryView view = repository.getOne(event.getInventoryId());
        repository.save(new InventoryView(event.getInventoryId(), event.getRemained()));
    }

    @EventHandler
    public void on(Events.InventoryDecreasedEvent event) {
        InventoryView view = repository.getOne(event.getInventoryId());
        repository.save(new InventoryView(event.getInventoryId(), event.getRemained()));

    }

    @QueryHandler
    public List<InventoryView> handle(Queries.AllInventoryViewQuery query) {
        return repository.findAll();
    }

    @QueryHandler
    public InventoryView handle(Queries.FindInventoryViewQuery query) {
        return repository.getOne(query.getInventoryId());
    }


}
