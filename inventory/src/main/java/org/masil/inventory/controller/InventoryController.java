package org.masil.inventory.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.masil.inventory.commands.Commands;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/inventories")
public class InventoryController {

    private final CommandGateway commandGateway;


    @PostMapping
    public void create() {
        commandGateway.send(new Commands.CreateInventoryCommand(UUID.randomUUID()));
    }

    public enum ModifyQuantityType {
        INC, DESC
    }

    @Getter
    public static class ModifyQuantityRequest {
        private ModifyQuantityType type;
        private int quantity;
    }

    @PutMapping("/{inventoryId}")
    public void modifyQuantity(@PathVariable UUID inventoryId, @RequestBody ModifyQuantityRequest request) {

        Object command = request.getType() == ModifyQuantityType.INC ?
                new Commands.IncreaseInventoryCommand(inventoryId, request.getQuantity()):
                new Commands.DecreaseInventoryCommand(inventoryId, request.getQuantity());

        commandGateway.send(command);
    }

}
