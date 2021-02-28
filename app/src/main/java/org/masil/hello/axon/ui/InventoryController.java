package org.masil.hello.axon.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.masil.hello.axon.inventory.Commands;
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

    @Data
    public static class ModifyRequest {
        private ModifyType type;
        private int quantity;
    }

    public enum ModifyType {
        INC, DESC
    }

    @PutMapping("/{inventoryId}")
    public void modify(@PathVariable UUID inventoryId, @RequestBody ModifyRequest request) {
        final Object command =
                request.type == ModifyType.INC ?
                        new Commands.IncreaseInventoryCommand(inventoryId, request.quantity) :
                        new Commands.DecreaseInventoryCommand(inventoryId, request.quantity);

        commandGateway.send(command);
    }
}
