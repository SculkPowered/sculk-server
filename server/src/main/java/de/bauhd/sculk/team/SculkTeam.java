package de.bauhd.sculk.team;

import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.protocol.packet.play.UpdateTeams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public final class SculkTeam implements Team {

    private final SculkServer server;
    private final String name;
    private final List<String> entries;
    private Component displayName;
    private NamedTextColor color;
    private Component prefix;
    private Component suffix;

    public SculkTeam(final SculkServer server,
                     final String name,
                     final List<String> entries,
                     final Component displayName,
                     final NamedTextColor color,
                     final Component prefix,
                     final Component suffix) {
        this.server = server;
        this.name = name;
        this.entries = entries;
        this.displayName = displayName;
        this.color = color;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(@NotNull Component displayName) {
        this.displayName = displayName;
        this.updateTeam();
    }

    @Override
    public @NotNull NamedTextColor getColor() {
        return this.color;
    }

    @Override
    public void setColor(@Nullable NamedTextColor color) {
        this.color = color;
        this.updateTeam();
    }

    @Override
    public @NotNull Component getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(@NotNull Component prefix) {
        this.prefix = prefix;
        this.updateTeam();
    }

    @Override
    public @NotNull Component getSuffix() {
        return this.suffix;
    }

    @Override
    public void setSuffix(@NotNull Component suffix) {
        this.suffix = suffix;
        this.updateTeam();
    }

    @Override
    public void addEntry(@NotNull String entry) {
        this.server.sendAll(new UpdateTeams(this, (byte) 3, new String[]{entry}));
        this.entries.add(entry);
    }

    @Override
    public void addEntries(@NotNull String... entries) {
        this.server.sendAll(new UpdateTeams(this, (byte) 3, entries));
        Collections.addAll(this.entries, entries);
    }

    @Override
    public void removeEntry(@NotNull String entry) {
        this.server.sendAll(new UpdateTeams(this, (byte) 4, new String[]{entry}));
        this.entries.remove(entry);
    }

    @Override
    public void removeEntries(@NotNull String... entries) {
        this.server.sendAll(new UpdateTeams(this, (byte) 4, entries));
        for (final var entry : entries) {
            this.entries.remove(entry);
        }
    }

    public List<String> entries() {
        return this.entries;
    }

    private void updateTeam() {
        this.server.sendAll(new UpdateTeams(this, (byte) 2, null));
    }
}
