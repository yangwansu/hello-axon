package org.masil.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

public class Commands {

    @Getter
    @AllArgsConstructor
    public static class CreateInventoryCommand {
        @TargetAggregateIdentifier
        private UUID inventoryId;
    }
    @Getter
    @AllArgsConstructor
    public static class IncreaseInventoryCommand {
        @TargetAggregateIdentifier
        private UUID inventoryId;
        private int delta;
    }

    @Getter
    @Aggregate
    @AllArgsConstructor
    public static class DecreaseInventoryCommand {
        @TargetAggregateIdentifier
        private UUID inventoryId;
        private int delta;
    }
}
