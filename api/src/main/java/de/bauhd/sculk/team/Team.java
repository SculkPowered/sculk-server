package de.bauhd.sculk.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Team {

    /**
     * Gets the name of the team.
     *
     * @return the name of the team
     * @since 1.0.0
     */
    @NotNull String getName();

    /**
     * Gets the display name of the team
     *
     * @return the display name of the team
     * @since 1.0.0
     */
    @NotNull Component getDisplayName();

    /**
     * Sets the specified component as the display name of the team.
     *
     * @param displayName the component to set
     * @since 1.0.0
     */
    void setDisplayName(@NotNull Component displayName);

    /**
     * Gets the color of the team
     *
     * @return the color of the team
     * @since 1.0.0
     */
    @Nullable NamedTextColor getColor();

    /**
     * Sets the specified color as the color of the team.
     *
     * @param color the color to set
     * @since 1.0.0
     */
    void setColor(@Nullable NamedTextColor color);

    /**
     * Gets the prefix of the team.
     *
     * @return the prefix of the team
     * @since 1.0.0
     */
    @NotNull Component getPrefix();

    /**
     * Sets the specified component as the prefix of the team.
     *
     * @param prefix the component to set
     * @since 1.0.0
     */
    void setPrefix(@NotNull Component prefix);

    /**
     * Gets the suffix of the team.
     *
     * @return the suffix of the team
     * @since 1.0.0
     */
    @NotNull Component getSuffix();

    /**
     * Sets the specified component as the suffix of the team.
     *
     * @param suffix the component to set
     * @since 1.0.0
     */
    void setSuffix(@NotNull Component suffix);

    /**
     * Adds a new entry to the team.
     *
     * @since 1.0.0
     */
    void addEntry(@NotNull String entry);

    /**
     * Adds new entries to the team.
     *
     * @since 1.0.0
     */
    void addEntries(@NotNull String... entities);

    /**
     * Removes the entry.
     *
     * @since 1.0.0
     */
    void removeEntry(@NotNull String entry);

    /**
     * Removes the entries.
     *
     * @since 1.0.0
     */
    void removeEntries(@NotNull String... entities);

    /**
     * Creates a new builder.
     *
     * @return the new created builder
     * @since 1.0.0
     */
    static @NotNull Builder builder() {
        return new Builder();
    }

    final class Builder {

        private @NotNull String name = "";
        private @NotNull Component displayName = Component.empty();
        private @Nullable NamedTextColor color;
        private @NotNull Component prefix = Component.empty();
        private @NotNull Component suffix = Component.empty();
        private @Nullable String[] entries;

        public @NotNull Builder name(@NotNull String name) {
            this.name = name;
            return this;
        }

        public @NotNull String name() {
            return this.name;
        }

        public @NotNull Builder displayName(@NotNull Component displayName) {
            this.displayName = displayName;
            return this;
        }

        public @NotNull Component displayName() {
            return this.displayName;
        }

        public @NotNull Builder color(NamedTextColor color) {
            this.color = color;
            return this;
        }

        public @Nullable NamedTextColor color() {
            return this.color;
        }

        public @NotNull Builder prefix(@NotNull Component prefix) {
            this.prefix = prefix;
            return this;
        }

        public @NotNull Component prefix() {
            return this.prefix;
        }

        public @NotNull Builder suffix(@NotNull Component suffix) {
            this.suffix = suffix;
            return this;
        }

        public @NotNull Component suffix() {
            return this.suffix;
        }

        public @NotNull Builder entries(@NotNull String... entries) {
            this.entries = entries;
            return this;
        }

        public @Nullable String[] entries() {
            return this.entries;
        }
    }
}
