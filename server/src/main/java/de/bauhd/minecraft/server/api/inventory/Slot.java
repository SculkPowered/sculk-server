package de.bauhd.minecraft.server.api.inventory;

import de.bauhd.minecraft.server.api.world.Material;

public final class Slot {

    private Material material;
    private int count;

    public Slot(Material material, int count) {
        this.material = material;
        this.count = count;
    }

    public Material material() {
        return this.material;
    }

    public void material(final Material material) {
        this.material = material;
    }

    public int count() {
        return this.count;
    }

    public void count(final int count) {
        this.count = count;
    }
}
