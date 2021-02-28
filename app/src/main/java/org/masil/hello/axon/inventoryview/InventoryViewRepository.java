package org.masil.hello.axon.inventoryview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryViewRepository extends JpaRepository<InventoryView, UUID> {
}
