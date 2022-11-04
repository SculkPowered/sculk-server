package de.bauhd.minecraft.server.api.world;

public final class Block {

    private Material material;

    public Block(final Material material) {
        this.material = material;
    }

    public Material material() {
        return this.material;
    }

    public void material(Material material) {
        this.material = material;
    }
}
