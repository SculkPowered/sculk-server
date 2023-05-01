package de.bauhd.minecraft.server.world.block;

import de.bauhd.minecraft.server.inventory.item.Material;

public final class MinecraftBlock {

    private Material material;

    public MinecraftBlock(final Material material) {
        this.material = material;
    }

    public Material material() {
        return this.material;
    }

    public void material(Material material) {
        this.material = material;
    }
}
