package org.masil.hello.axon.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.masil.hello.axon.inventory.InventoryController.ModifyQuantityRequestType.INC;

@AllArgsConstructor
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final CommandGateway commandGateway;

    @PostMapping
    public void create() {
        commandGateway.send(new Commands.CreateInventoryCommand(UUID.randomUUID()));
    }

    @Getter
    @AllArgsConstructor
    public static class ModifyQuantityRequest {
        private final ModifyQuantityRequestType type;
        private final int quantity;
    }

    public enum ModifyQuantityRequestType {
        INC,DESC
    }

    @PutMapping("/{inventoryId}")
    public void modifyQuantity(@PathVariable UUID inventoryId, @RequestBody ModifyQuantityRequest request) {
        Object command = request.getType() == INC ?
                new Commands.IncreaseInventoryCommand(inventoryId, request.getQuantity()):
                new Commands.DecreaseInventoryCommand(inventoryId, request.getQuantity());

        commandGateway.send(command);
    }

}
