package org.masil.inventory.view.projector;


import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.masil.inventory.events.Events;
import org.masil.inventory.view.entity.InventoryView;
import org.masil.inventory.view.entity.InventoryViewRepository;
import org.masil.inventory.view.queries.Queries;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class InventoryViewProjector {

    private final InventoryViewRepository repository;

    @EventHandler
    public void on(Events.InventoryCreatedEvent event) {
        InventoryView view = new InventoryView(event.getInventoryId(), 0);
        repository.save(view);
    }

    @EventHandler
    public void on(Events.InventoryIncreasedEvent event) {
        InventoryView view = repository.getOne(event.getInventoryId());

        InventoryView updated = new InventoryView(view.getId(), view.getQuantity() + event.getQuantity());

        repository.save(updated);
    }

    @EventHandler
    public void on(Events.InventoryDecreasedEvent event) {
        InventoryView view = repository.getOne(event.getInventoryId());

        InventoryView updated = new InventoryView(view.getId(), view.getQuantity() - event.getQuantity());

        repository.save(updated);
    }

    @QueryHandler
    public InventoryView handle(Queries.FindInventoryViewQuery query){
        return repository.getOne(query.getInventoryId());
    }

    @QueryHandler
    public List<InventoryView> handle(Queries.AllInventoriesQuery query){
        return repository.findAll();
    }

}
