package org.masil.hello.axon.inventory.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class InitInventoryCommand {

    @TargetAggregateIdentifier
    private final UUID inventoryId;
}
