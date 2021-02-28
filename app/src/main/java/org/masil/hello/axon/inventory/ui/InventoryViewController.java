package org.masil.hello.axon.inventory.ui;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.masil.hello.axon.inventory.commands.DecreaseInventoryCommand;
import org.masil.hello.axon.inventory.commands.IncreaseInventoryCommand;
import org.masil.hello.axon.inventory.commands.InitInventoryCommand;
import org.masil.hello.axon.inventory.query.AllInventoryQuery;
import org.masil.hello.axon.inventory.query.FindInventoryQuery;
import org.masil.hello.axon.inventory.query.InventoryView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.masil.hello.axon.inventory.ui.InventoryViewController.ModifyType.INC;

@AllArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryViewController {


    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping()
    public void init() {
        commandGateway.send(new InitInventoryCommand(UUID.randomUUID()));
    }


    @Getter
    public static class InventoryModify {
        private ModifyType type;
        private int quantity;
    }


    enum ModifyType{
        INC,DESC
    }
    @PutMapping("/{inventoryId}")
    public void modifyInventory(@PathVariable UUID inventoryId, @RequestBody InventoryModify modify) {
        if(modify.type == INC ) {
            commandGateway.send(new IncreaseInventoryCommand(inventoryId,modify.getQuantity()));
        }else {
            commandGateway.send(new DecreaseInventoryCommand(inventoryId,modify.getQuantity()));
        }
    }

    @GetMapping("/{inventoryId}")
    public CompletableFuture<InventoryView> find(@PathVariable UUID inventoryId) {
        return queryGateway.query(new FindInventoryQuery(inventoryId), ResponseTypes.instanceOf(InventoryView.class));
    }

    @GetMapping()
    public CompletableFuture<List<InventoryView>> findAll() {
        return queryGateway.query(new AllInventoryQuery(), ResponseTypes.multipleInstancesOf(InventoryView.class));
    }


}
