package org.masil.hello.axon.inventory.view.app;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("/inventories")
public class InventoryViewController {

    private final QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<InventoryView>> all() {
        return queryGateway.query(new Queries.AllInventoryViewQuery(), ResponseTypes.multipleInstancesOf(InventoryView.class));
    }

    @GetMapping("/{inventoryId}")
    public CompletableFuture<InventoryView> find(@PathVariable UUID inventoryId) {
        return queryGateway.query(new Queries.FindInventoryViewQuery(inventoryId), ResponseTypes.instanceOf(InventoryView.class));
    }

}
