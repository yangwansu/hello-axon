package org.masil.inventory.view.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryViewRepository extends JpaRepository<InventoryView, UUID> {
}
