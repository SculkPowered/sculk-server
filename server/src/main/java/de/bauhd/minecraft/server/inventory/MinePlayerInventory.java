package de.bauhd.minecraft.server.inventory;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.inventory.item.ItemStack;
import de.bauhd.minecraft.server.protocol.packet.play.Equipment;
import de.bauhd.minecraft.server.protocol.packet.play.container.ContainerSlot;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class MinePlayerInventory extends MineInventory implements PlayerInventory {

    private final MinecraftPlayer player;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public MinePlayerInventory(final MinecraftPlayer player) {
        this.player = player;
    }

    @Override
    public @Nullable Component title() {
        return null;
    }

    @Override
    public @NotNull Type type() {
        return null;
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        super.setItem(index, itemStack);
        if (index < 9) {
            index += 36;
        } else if (index > 39) {
            index += 5;
        } else if (index > 35) {
            index = 8 - (index - 36);
        }
        this.player.send(new ContainerSlot((byte) 0, 1, (short) index, itemStack));
    }

    @Override
    public @Nullable ItemStack getItemInMainHand() {
        return this.getItem(this.player.getHeldItemSlot());
    }

    @Override
    public @Nullable ItemStack getItemInOffHand() {
        return this.getItem(40);
    }

    @Override
    public void setItemInOffHand(@Nullable ItemStack item) {
        this.setItem(40, item);
    }

    @Override
    public void setHelmet(@Nullable ItemStack helmet) {
        this.helmet = helmet;
        this.player.sendViewersAndSelf(new Equipment(this.player.getId(), Map.of(5, this.helmet)));
    }

    @Override
    public @Nullable ItemStack getHelmet() {
        return this.helmet;
    }

    @Override
    public void setChestplate(@Nullable ItemStack chestplate) {
        this.chestplate = chestplate;
        this.player.sendViewersAndSelf(new Equipment(this.player.getId(), Map.of(4, this.chestplate)));
    }

    @Override
    public @Nullable ItemStack getChestplate() {
        return this.chestplate;
    }

    @Override
    public void setLeggings(@Nullable ItemStack leggings) {
        this.leggings = leggings;
        this.player.sendViewersAndSelf(new Equipment(this.player.getId(), Map.of(3, this.leggings)));
    }

    @Override
    public @Nullable ItemStack getLeggings() {
        return this.leggings;
    }

    @Override
    public void setBoots(@Nullable ItemStack boots) {
        this.boots = boots;
        this.player.sendViewersAndSelf(new Equipment(this.player.getId(), Map.of(2, this.boots)));
    }

    @Override
    public @Nullable ItemStack getBoots() {
        return this.boots;
    }
}
