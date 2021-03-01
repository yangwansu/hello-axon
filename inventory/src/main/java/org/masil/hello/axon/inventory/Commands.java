package org.masil.hello.axon.inventory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Commands {


    @Getter
    @AllArgsConstructor
    public static class CreateInventoryCommand {
        @TargetAggregateIdentifier
        private final UUID inventoryId;
    }
    @Getter
    @AllArgsConstructor
    public static class IncreaseInventoryCommand {
        @TargetAggregateIdentifier
        private final UUID inventoryId;
        private final int quantity;
    }

    @Getter
    @AllArgsConstructor
    public static class DecreaseInventoryCommand {
        @TargetAggregateIdentifier
        private final UUID inventoryId;
        private final int quantity;
    }

}
