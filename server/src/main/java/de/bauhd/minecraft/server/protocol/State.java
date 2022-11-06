package de.bauhd.minecraft.server.protocol;

import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.handshake.Handshake;
import de.bauhd.minecraft.server.protocol.packet.login.*;
import de.bauhd.minecraft.server.protocol.packet.play.*;
import de.bauhd.minecraft.server.protocol.packet.play.scoreboard.DisplayObjective;
import de.bauhd.minecraft.server.protocol.packet.play.scoreboard.UpdateObjectives;
import de.bauhd.minecraft.server.protocol.packet.play.scoreboard.UpdateScore;
import de.bauhd.minecraft.server.protocol.packet.play.title.Subtitle;
import de.bauhd.minecraft.server.protocol.packet.play.title.Title;
import de.bauhd.minecraft.server.protocol.packet.play.title.TitleAnimationTimes;
import de.bauhd.minecraft.server.protocol.packet.status.StatusPing;
import de.bauhd.minecraft.server.protocol.packet.status.StatusRequest;
import de.bauhd.minecraft.server.protocol.packet.status.StatusResponse;
import io.netty5.util.collection.IntObjectHashMap;
import io.netty5.util.collection.IntObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Supplier;

import static de.bauhd.minecraft.server.protocol.Protocol.Direction;
import static de.bauhd.minecraft.server.protocol.Protocol.Version;
import static de.bauhd.minecraft.server.protocol.Protocol.Version.*;

public enum State {

    HANDSHAKE {
        {
            this.serverBound.register(Handshake.class, Handshake::new,
                    this.map(0x00, MINECRAFT_1_7_2)
            );
        }
    },
    STATUS {
        {
            this.serverBound.register(StatusRequest.class, () -> StatusRequest.INSTANCE,
                    this.map(0x00, MINECRAFT_1_7_2)
            );
            this.serverBound.register(StatusPing.class, StatusPing::new,
                    this.map(0x01, MINECRAFT_1_7_2)
            );

            this.clientBound.register(StatusResponse.class,
                    this.map(0x00, MINECRAFT_1_7_2)
            );
            this.clientBound.register(StatusPing.class,
                    this.map(0x01, MINECRAFT_1_7_2)
            );
        }
    },
    LOGIN {
        {
            this.serverBound.register(LoginStart.class, LoginStart::new,
                    this.map(0x00, MINECRAFT_1_7_2)
            );
            this.serverBound.register(EncryptionResponse.class, EncryptionResponse::new,
                    this.map(0x01, MINECRAFT_1_7_2)
            );

            this.clientBound.register(Disconnect.class,
                    this.map(0x00, MINECRAFT_1_7_2)
            );
            this.clientBound.register(EncryptionRequest.class,
                    this.map(0x01, MINECRAFT_1_7_2)
            );
            this.clientBound.register(LoginSuccess.class,
                    this.map(0x02, MINECRAFT_1_7_2)
            );
        }
    },
    PLAY {
        {
            this.serverBound.disableFallback();
            this.clientBound.disableFallback();

            this.serverBound.register(ConfirmTeleportation.class, ConfirmTeleportation::new,
                    this.map(0x00, MINECRAFT_1_19)
            );
            this.serverBound.register(ChatCommand.class, ChatCommand::new,
                    this.map(0x03, MINECRAFT_1_19)
            );
            this.serverBound.register(ChatMessage.class, ChatMessage::new,
                    this.map(0x04, MINECRAFT_1_19)
            );
            this.serverBound.register(ClientInformation.class, ClientInformation::new,
                    this.map(0x07, MINECRAFT_1_19),
                    this.map(0x08, MINECRAFT_1_19_1)
            );
            this.serverBound.register(PluginMessage.class, PluginMessage::new,
                    this.map(0x0C, MINECRAFT_1_19),
                    this.map(0x0D, MINECRAFT_1_19_1)
            );
            this.serverBound.register(KeepAlive.class, KeepAlive::new,
                    this.map(0x11, MINECRAFT_1_19),
                    this.map(0x12, MINECRAFT_1_19_1)
            );
            this.serverBound.register(PlayerCommand.class, PlayerCommand::new,
                    this.map(0x1D, MINECRAFT_1_19)
            );
            this.serverBound.register(HeldItem.class, HeldItem::new,
                    this.map(0x27, MINECRAFT_1_19)
            );

            this.clientBound.register(SpawnEntity.class,
                    this.map(0x00, MINECRAFT_1_19)
            );
            this.clientBound.register(SpawnPlayer.class,
                    this.map(0x02, MINECRAFT_1_19)
            );
            this.clientBound.register(BossBar.class,
                    this.map(0x0A, MINECRAFT_1_19)
            );
            this.clientBound.register(Commands.class,
                    this.map(0x0F, MINECRAFT_1_19)
            );
            this.clientBound.register(ContainerContent.class,
                    this.map(0x11, MINECRAFT_1_19)
            );
            this.clientBound.register(PluginMessage.class,
                    this.map(0x15, MINECRAFT_1_19),
                    this.map(0x16, MINECRAFT_1_19_1)
            );
            this.clientBound.register(Disconnect.class,
                    this.map(0x17, MINECRAFT_1_19),
                    this.map(0x19, MINECRAFT_1_19_1)
            );
            this.clientBound.register(KeepAlive.class,
                    this.map(0x21, MINECRAFT_1_18_2),
                    this.map(0x1E, MINECRAFT_1_19),
                    this.map(0x20, MINECRAFT_1_19_1)
            );
            this.clientBound.register(ChunkDataAndUpdateLight.class,
                    this.map(0x22, MINECRAFT_1_18_2),
                    this.map(0x1F, MINECRAFT_1_19),
                    this.map(0x21, MINECRAFT_1_19_1)
            );
            this.clientBound.register(Login.class,
                    this.map(0x26, MINECRAFT_1_18_2),
                    this.map(0x23, MINECRAFT_1_19),
                    this.map(0x25, MINECRAFT_1_19_1)
            );
            this.clientBound.register(PlayerInfo.class,
                    this.map(0x34, MINECRAFT_1_19),
                    this.map(0x37, MINECRAFT_1_19_1)
            );
            this.clientBound.register(SynchronizePlayerPosition.class,
                    this.map(0x38, MINECRAFT_1_18_2),
                    this.map(0x36, MINECRAFT_1_19),
                    this.map(0x39, MINECRAFT_1_19_1)
            );
            this.clientBound.register(ActionBar.class,
                    this.map(0x40, MINECRAFT_1_19),
                    this.map(0x43, MINECRAFT_1_19_1)
            );
            this.clientBound.register(HeldItem.class,
                    this.map(0x47, MINECRAFT_1_19),
                    this.map(0x4A, MINECRAFT_1_19_1)
            );
            this.clientBound.register(CenterChunk.class,
                    this.map(0x48, MINECRAFT_1_19),
                    this.map(0x4B, MINECRAFT_1_19_1)
            );
            this.clientBound.register(RenderDistance.class,
                    this.map(0x49, MINECRAFT_1_19),
                    this.map(0x4C, MINECRAFT_1_19_1)
            );
            this.clientBound.register(SpawnPosition.class,
                    this.map(0x4B, MINECRAFT_1_18_2),
                    this.map(0x4A, MINECRAFT_1_19),
                    this.map(0x4D, MINECRAFT_1_19_1)
            );
            this.clientBound.register(DisplayObjective.class,
                    this.map(0x4C, MINECRAFT_1_19),
                    this.map(0x4F, MINECRAFT_1_19_1)
            );
            this.clientBound.register(Experience.class,
                    this.map(0x51, MINECRAFT_1_19),
                    this.map(0x54, MINECRAFT_1_19_1)
            );
            this.clientBound.register(Health.class,
                    this.map(0x52, MINECRAFT_1_19),
                    this.map(0x55, MINECRAFT_1_19_1)
            );
            this.clientBound.register(UpdateObjectives.class,
                    this.map(0x53, MINECRAFT_1_19),
                    this.map(0x56, MINECRAFT_1_19_1)
            );
            this.clientBound.register(UpdateScore.class,
                    this.map(0x56, MINECRAFT_1_19),
                    this.map(0x59, MINECRAFT_1_19_1)
            );
            this.clientBound.register(SimulationDistance.class,
                    this.map(0x57, MINECRAFT_1_19),
                    this.map(0x5A, MINECRAFT_1_19_1)
            );
            this.clientBound.register(Subtitle.class,
                    this.map(0x58, MINECRAFT_1_19),
                    this.map(0x5B, MINECRAFT_1_19_1)
            );
            this.clientBound.register(Title.class,
                    this.map(0x5A, MINECRAFT_1_19),
                    this.map(0x5D, MINECRAFT_1_19_1)

            );
            this.clientBound.register(TitleAnimationTimes.class,
                    this.map(0x5B, MINECRAFT_1_19),
                    this.map(0x5E, MINECRAFT_1_19_1)
            );
            this.clientBound.register(SystemChatMessage.class,
                    this.map(0x5F, MINECRAFT_1_19)
            );
            this.clientBound.register(TabListHeaderFooter.class,
                    this.map(0x60, MINECRAFT_1_19),
                    this.map(0x63, MINECRAFT_1_19_1)
            );
            this.clientBound.register(UpdateAttributes.class,
                    this.map(0x64, MINECRAFT_1_18_2),
                    this.map(0x65, MINECRAFT_1_19),
                    this.map(0x68, MINECRAFT_1_19_1)
                    //this.map(0x68, MINECRAFT_1_19_1)
            );
            this.clientBound.register(UpdateRecipes.class,
                    this.map(0x67, MINECRAFT_1_19),
                    this.map(0x6A, MINECRAFT_1_19_1)
            );
            this.clientBound.register(UpdateTags.class,
                    this.map(0x68, MINECRAFT_1_19),
                    this.map(0x6B, MINECRAFT_1_19_1)
            );
        }
    };

    protected final PacketRegistry clientBound = new PacketRegistry(Direction.CLIENTBOUND);
    protected final PacketRegistry serverBound = new PacketRegistry(Direction.SERVERBOUND);

    public static class PacketRegistry {

        private final Map<Version, ProtocolRegistry> versions;
        private boolean fallback = true;

        PacketRegistry(Direction direction) {

            final Map<Version, ProtocolRegistry> mutableVersions = new EnumMap<>(Version.class);
            for (final var version : Version.values()) {
                mutableVersions.put(version, new ProtocolRegistry(version));
            }

            this.versions = Collections.unmodifiableMap(mutableVersions);
        }

        protected ProtocolRegistry getProtocolRegistry(final Version version) {
            final ProtocolRegistry registry = this.versions.get(version);
            if (registry == null) {
                if (this.fallback) {
                    return this.getProtocolRegistry(Version.MINIMUM);
                }
                throw new IllegalArgumentException("Could not find data for protocol version " + version);
            }
            return registry;
        }

        protected <P extends Packet> void register(final Class<P> clazz,
                                                   final MappedPacket... mappings) {
            this.register(clazz, null, mappings);
        }

        protected <P extends Packet> void register(final Class<P> clazz,
                                                   final Supplier<P> packetSupplier,
                                                   final MappedPacket... mappings) {
            for (var i = 0; i < mappings.length; i++) {
                final var current = mappings[i];
                final var next = (i + 1 < mappings.length) ? mappings[i + 1] : current;
                final var from = current.version();
                final var lastValid = current.toVersion();

                if (lastValid != null) {
                    if (next != current) {
                        throw new IllegalArgumentException("Cannot add a mapping after last valid mapping");
                    }
                    if (from.compareTo(lastValid) > 0) {
                        throw new IllegalArgumentException(
                                "Last mapping version cannot be higher than highest mapping version");
                    }
                }
                final var to = current == next ? lastValid != null
                        ? lastValid : Version.MAXIMUM : next.version();

                if (from.compareTo(to) >= 0 && from != Version.MAXIMUM) {
                    throw new IllegalArgumentException(String.format(
                            "Next mapping version (%s) should be lower then current (%s)", to, from));
                }

                for (final var protocol : EnumSet.range(from, to)) {
                    if (protocol == to && next != current) {
                        break;
                    }
                    final var registry = this.versions.get(protocol);
                    if (registry == null) {
                        throw new IllegalArgumentException("Unknown protocol version "
                                + current.version());
                    }

                    if (registry.packetIdToSupplier.containsKey(current.id())) {
                        throw new IllegalArgumentException("Can not register class " + clazz.getSimpleName()
                                + " with id " + current.id() + " for " + registry.version
                                + " because another packet is already registered");
                    }

                    if (registry.packetClassToId.containsKey(clazz)) {
                        throw new IllegalArgumentException(clazz.getSimpleName()
                                + " is already registered for version " + registry.version);
                    }

                    if (packetSupplier != null) {
                        registry.packetIdToSupplier.put(current.id(), packetSupplier);
                    }
                    registry.packetClassToId.put(clazz, current.id());
                }
            }
        }

        protected void disableFallback() {
            this.fallback = false;
        }

        public static class ProtocolRegistry {

            public final Protocol.Version version;
            public final IntObjectMap<Supplier<? extends Packet>> packetIdToSupplier = new IntObjectHashMap<>(16, 0.5f);
            protected final Object2IntMap<Class<? extends Packet>> packetClassToId = new Object2IntOpenHashMap<>(16, 0.5f);

            ProtocolRegistry(final Protocol.Version version) {
                this.version = version;
                this.packetClassToId.defaultReturnValue(-1);
            }

            public Packet createPacket(final int id) {
                final var supplier = this.packetIdToSupplier.get(id);
                if (supplier != null) {
                    return supplier.get();
                }
                return null;
            }

            public int getPacketId(final Packet packet) {
                final var id = this.packetClassToId.getInt(packet.getClass());
                if (id == Integer.MIN_VALUE) {
                    System.out.println("err min value dies das");
                }
                return id;
            }
        }
    }

    protected MappedPacket map(final int id,
                               final Protocol.Version version) {
        return new MappedPacket(id, version, null);
    }

    protected MappedPacket map(final int id,
                               final Protocol.Version version,
                               final Protocol.Version toVersion) {
        return new MappedPacket(id, version, toVersion);
    }

    protected MappedPacket map(final int id,
                               final Protocol.Version version,
                               final Protocol.Version toVersion,
                               final boolean encodeOnly) {
        return new MappedPacket(id, version, toVersion);
    }

    private record MappedPacket(
            int id,
            @NotNull Protocol.Version version,
            @Nullable Protocol.Version toVersion
    ) {
    }

}
