package org.masil.hello.axon.inventory.query;

import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.masil.hello.axon.inventory.events.InventoryDecreasedEvent;
import org.masil.hello.axon.inventory.events.InventoryIncreasedEvent;
import org.masil.hello.axon.inventory.events.InventoryInitedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InventoryViewProjector {

    private final InventoryViewRepository repository;

    @EventHandler
    public void on(InventoryInitedEvent event) {
        InventoryView view = new InventoryView(event.getInventoryId(), 0);
        repository.save(view);
    }

    @EventHandler
    public void on(InventoryIncreasedEvent event) {
        Optional<InventoryView> view = repository.findById(event.getInventoryId());
        view.ifPresent(v -> repository.save(new InventoryView(v.getInventoryId(), v.getQuantity() + event.getQuantity())));
    }

    @EventHandler
    public void on(InventoryDecreasedEvent event) {
        Optional<InventoryView> view = repository.findById(event.getInventoryId());
        view.ifPresent(v -> repository.save(new InventoryView(v.getInventoryId(), v.getQuantity() - event.getQuantity())));
    }

    @QueryHandler
    public InventoryView handle(FindInventoryQuery query) {
        return repository.findById(query.getInventoryId()).orElse(null);
    }

    @QueryHandler
    public List<InventoryView> handle(AllInventoryQuery query) {
        return repository.findAll();
    }

}
