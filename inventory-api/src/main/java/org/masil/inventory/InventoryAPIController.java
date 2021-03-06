package org.masil.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.masil.inventory.InventoryAPIController.ModifyQuantityType.INC;

@AllArgsConstructor
@RestController
@RequestMapping("/inventories")
public class InventoryAPIController {

    private final CommandGateway commandGateway;

    @PostMapping
    public void create() {
        commandGateway.send(new Commands.CreateInventoryCommand(UUID.randomUUID()));
    }


    public enum ModifyQuantityType {
        INC,DESC
    }

    @Getter
    @AllArgsConstructor
    public static class ModifyQuantityRequest {
        private final ModifyQuantityType type;
        private final int delta;

        public Object command(UUID inventoryId) {
            return type == INC ? new Commands.IncreaseInventoryCommand(inventoryId, delta) : new Commands.DecreaseInventoryCommand(inventoryId, delta);
        }
    }


    @PutMapping("/{inventoryId}")
    public void modifyQuantity(@PathVariable UUID inventoryId, @RequestBody ModifyQuantityRequest request) {
        commandGateway.send(request.command(inventoryId));
    }

}
