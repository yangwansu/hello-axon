package org.masil.inventory.commands;


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
    public static final class CreateInventoryCommand {
        @TargetAggregateIdentifier
        private final UUID inventoryId;
    }

    @Getter
    @AllArgsConstructor
    public static final class IncreaseInventoryCommand {
        @TargetAggregateIdentifier
        private final UUID inventoryId;
        private final int quantity;
    }

    @Getter
    @AllArgsConstructor
    public static final class DecreaseInventoryCommand {
        @TargetAggregateIdentifier
        private final UUID inventoryId;
        private final int quantity;
    }
}
