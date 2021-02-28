package org.masil.hello.axon.ui;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.masil.hello.axon.inventoryview.InventoryView;
import org.masil.hello.axon.inventoryview.Queries;
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
public class InventoryQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/{inventoryId}")
    public CompletableFuture<InventoryView> handle(@PathVariable UUID inventoryId) {
        return queryGateway.query(new Queries.FindInventoryQuery(inventoryId), ResponseTypes.instanceOf(InventoryView.class));
    }
    @GetMapping
    public CompletableFuture<List<InventoryView>> handle() {
        return queryGateway.query(new Queries.AllInventoryQuery(), ResponseTypes.multipleInstancesOf(InventoryView.class));
    }
}
