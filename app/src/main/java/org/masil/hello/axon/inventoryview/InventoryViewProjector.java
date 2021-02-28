package org.masil.hello.axon.inventoryview;


import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.masil.hello.axon.inventory.Events;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class InventoryViewProjector {

    private final InventoryViewRepository repository;

    @EventHandler
    void on(Events.InventoryCreatedEvent event) {
        repository.save(new InventoryView(event.getInventoryId(), 0));
    }

    @EventHandler
    void on(Events.InventoryIncreasedEvent event) {
        // TODO 정합성
        InventoryView view = repository.findById(event.getInventoryId()).orElseThrow(RuntimeException::new);
        //TODO view 에 setter 추가?
        //TODO 자동 저장되는지 확인필요
        InventoryView updated = new InventoryView(event.getInventoryId(), event.getQuantity() + view.getQuantity());
        repository.save(updated);
    }

    @EventHandler
    void on(Events.InventoryDecreasedEvent event) {
        // TODO 정합성
        InventoryView view = repository.findById(event.getInventoryId()).orElseThrow(RuntimeException::new);
        InventoryView updated = new InventoryView(event.getInventoryId(), view.getQuantity() - event.getQuantity());
        repository.save(updated);
    }


    @QueryHandler
    private Optional<InventoryView> handle(Queries.FindInventoryQuery query){
        return repository.findById(query.getInventoryId());
    }

    @QueryHandler
    private List<InventoryView> handle(Queries.AllInventoryQuery query) {
        return repository.findAll();
    }

}
