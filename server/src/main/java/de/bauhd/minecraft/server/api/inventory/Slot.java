package de.bauhd.minecraft.server.api.inventory;

import de.bauhd.minecraft.server.api.world.Material;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class Slot {

    private int materialId;  // TODO remove
    private Material material;
    private int count;
    private CompoundBinaryTag nbt;

    public Slot(final Material material, final int count) {
        this.material = material;
        this.count = count;
    }

    public Slot(final int materialId, final int count, final CompoundBinaryTag nbt) {
        this.materialId = materialId;
        this.count = count;
        this.nbt = nbt;
    }

    public Material material() {
        return this.material;
    }

    public int materialId() {
        return this.materialId;
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

    @Override
    public String toString() {
        return "Slot{" +
                "materialId=" + this.materialId +
                ", material=" + this.material +
                ", count=" + this.count +
                ", nbt=" + this.nbt +
                '}';
    }
}
