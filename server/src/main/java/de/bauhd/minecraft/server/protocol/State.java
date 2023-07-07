package de.bauhd.minecraft.server.protocol;

import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.handshake.Handshake;
import de.bauhd.minecraft.server.protocol.packet.login.*;
import de.bauhd.minecraft.server.protocol.packet.play.*;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockAction;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockEntityData;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockUpdate;
import de.bauhd.minecraft.server.protocol.packet.play.command.ChatCommand;
import de.bauhd.minecraft.server.protocol.packet.play.command.CommandSuggestionsResponse;
import de.bauhd.minecraft.server.protocol.packet.play.command.Commands;
import de.bauhd.minecraft.server.protocol.packet.play.container.*;
import de.bauhd.minecraft.server.protocol.packet.play.position.*;
import de.bauhd.minecraft.server.protocol.packet.play.scoreboard.DisplayObjective;
import de.bauhd.minecraft.server.protocol.packet.play.scoreboard.UpdateObjectives;
import de.bauhd.minecraft.server.protocol.packet.play.scoreboard.UpdateScore;
import de.bauhd.minecraft.server.protocol.packet.play.title.ClearTitles;
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

import java.util.function.Supplier;

public enum State {

    HANDSHAKE {
        {
            this.serverBound.register(Handshake.class, Handshake::new);
        }
    },
    STATUS {
        {
            this.serverBound.register(StatusRequest.class, () -> StatusRequest.INSTANCE);
            this.serverBound.register(StatusPing.class, StatusPing::new);

            this.clientBound.register(StatusResponse.class);
            this.clientBound.register(StatusPing.class);
        }
    },
    LOGIN {
        {
            this.serverBound.register(LoginStart.class, LoginStart::new);
            this.serverBound.register(EncryptionResponse.class, EncryptionResponse::new);
            this.serverBound.register(LoginPluginResponse.class, LoginPluginResponse::new);

            this.clientBound.register(Disconnect.class);
            this.clientBound.register(EncryptionRequest.class);
            this.clientBound.register(LoginSuccess.class);
            this.clientBound.register(CompressionPacket.class);
            this.clientBound.register(LoginPluginRequest.class);
        }
    },
    PLAY {
        {
            this.serverBound.register(ConfirmTeleportation.class, ConfirmTeleportation::new);
            this.serverBound.skip(); // Query Block Entity Tag
            this.serverBound.skip(); // Change Difficulty - only Single-player
            this.serverBound.skip(); // Message Acknowledgment
            this.serverBound.register(ChatCommand.class, ChatCommand::new);
            this.serverBound.register(ChatMessage.class, ChatMessage::new);
            this.serverBound.register(PlayerSession.class, PlayerSession::new); // Player Session
            this.serverBound.register(ClientCommand.class, ClientCommand::new);
            this.serverBound.register(ClientInformation.class, ClientInformation::new);
            this.serverBound.register(CommandSuggestionsRequest.class, CommandSuggestionsRequest::new);
            this.serverBound.register(ClickContainerButton.class, ClickContainerButton::new);
            this.serverBound.register(ClickContainer.class, ClickContainer::new);
            this.serverBound.register(CloseContainer.class, CloseContainer::new);
            this.serverBound.register(PluginMessage.class, PluginMessage::new);
            this.serverBound.register(EditBook.class, EditBook::new);
            this.serverBound.skip(); // Query Entity Tag
            this.serverBound.register(Interact.class, Interact::new);
            this.serverBound.skip(); // Jigsaw Generate
            this.serverBound.register(KeepAlive.class, KeepAlive::new);
            this.serverBound.skip(); // Lock Difficult
            this.serverBound.register(PlayerPosition.class, PlayerPosition::new);
            this.serverBound.register(PlayerPositionAndRotation.class, PlayerPositionAndRotation::new);
            this.serverBound.register(PlayerRotation.class, PlayerRotation::new);
            this.serverBound.register(PlayerOnGround.class, PlayerOnGround::new);
            this.serverBound.skip(); // Move Vehicle
            this.serverBound.skip(); // Paddle Boat
            this.serverBound.skip(); // Pick Item
            this.serverBound.skip(); // Place Recipe
            this.serverBound.register(PlayerAbilities.class, PlayerAbilities::new);
            this.serverBound.register(PlayerAction.class, PlayerAction::new);
            this.serverBound.register(PlayerCommand.class, PlayerCommand::new);
            this.serverBound.skip(); // Player Input
            this.serverBound.skip(); // Pong
            this.serverBound.register(RecipeBookSettings.class, RecipeBookSettings::new);
            this.serverBound.skip(); // Seen Recipe
            this.serverBound.skip(); // Rename Item
            this.serverBound.skip(); // Resource Pack
            this.serverBound.skip(); // Advancements
            this.serverBound.skip(); // Select Trade
            this.serverBound.skip(); // Beacon Effect
            this.serverBound.register(HeldItem.class, HeldItem::new);
            this.serverBound.skip(); // Program Command Block
            this.serverBound.skip(); // Program Command Block Minecart
            this.serverBound.register(CreativeModeSlot.class, CreativeModeSlot::new);
            this.serverBound.skip(); // Program Jigsaw Block
            this.serverBound.skip(); // Program Structure Block
            this.serverBound.skip(); // Update Sign
            this.serverBound.register(SwingArm.class, SwingArm::new);
            this.serverBound.register(TeleportToEntity.class, TeleportToEntity::new);
            this.serverBound.register(UseItemOn.class, UseItemOn::new);
            this.serverBound.register(UseItem.class, UseItem::new);

            this.clientBound.skip(); // Bundle Delimiter
            this.clientBound.register(SpawnEntity.class);
            this.clientBound.register(SpawnExperienceOrb.class);
            this.clientBound.register(SpawnPlayer.class);
            this.clientBound.register(EntityAnimation.class);
            this.clientBound.register(AwardStatistics.class);
            this.clientBound.skip(); // Acknowledge Block Change
            this.clientBound.skip(); // Block Destroy Stage
            this.clientBound.register(BlockEntityData.class);
            this.clientBound.register(BlockAction.class);
            this.clientBound.register(BlockUpdate.class);
            this.clientBound.register(BossBar.class);
            this.clientBound.register(ChangeDifficulty.class);
            this.clientBound.skip(); // Chunk Biomes
            this.clientBound.register(ClearTitles.class);
            this.clientBound.register(CommandSuggestionsResponse.class);
            this.clientBound.register(Commands.class);
            this.clientBound.register(CloseContainer.class);
            this.clientBound.register(ContainerContent.class);
            this.clientBound.register(ContainerProperty.class);
            this.clientBound.register(ContainerSlot.class);
            this.clientBound.register(Cooldown.class);
            this.clientBound.skip(); // Chat Suggestions
            this.clientBound.register(PluginMessage.class);
            this.clientBound.skip(); // Damage Event
            this.clientBound.skip(); // Delete Message
            this.clientBound.register(Disconnect.class);
            this.clientBound.skip(); // Disguised Chat Message
            this.clientBound.register(EntityEvent.class);
            this.clientBound.skip(); // Explosion
            this.clientBound.skip(); // Unload Chunk
            this.clientBound.register(GameEvent.class);
            this.clientBound.skip(); // Open Horse Screen
            this.clientBound.skip(); // Hurt Animation
            this.clientBound.skip(); // Initialize World Border
            this.clientBound.register(KeepAlive.class);
            this.clientBound.register(ChunkDataAndUpdateLight.class);
            this.clientBound.skip(); // World Event
            this.clientBound.skip(); // Particle
            this.clientBound.skip(); // Update Light
            this.clientBound.register(Login.class);
            this.clientBound.skip(); // Map Data
            this.clientBound.skip(); // Merchant Offers
            this.clientBound.register(EntityPosition.class);
            this.clientBound.register(EntityPositionAndRotation.class);
            this.clientBound.register(EntityRotation.class);
            this.clientBound.skip(); // Move Vehicle
            this.clientBound.skip(); // Open Book
            this.clientBound.register(OpenScreen.class);
            this.clientBound.skip(); // Open Sign Editor
            this.clientBound.skip(); // Ping
            this.clientBound.skip(); // Place Ghost Recipe
            this.clientBound.register(PlayerAbilities.class);
            this.clientBound.skip(); // Player Chat Message
            this.clientBound.skip(); // End Combat - was once used for Twitch
            this.clientBound.skip(); // Enter Combat - was once used for Twitch
            this.clientBound.skip(); // Combat Death
            this.clientBound.register(PlayerInfoRemove.class);
            this.clientBound.register(PlayerInfo.class);
            this.clientBound.skip(); // Look At
            this.clientBound.register(SynchronizePlayerPosition.class);
            this.clientBound.skip(); // Update Recipe Book
            this.clientBound.register(RemoveEntities.class);
            this.clientBound.skip(); // Remove Entity Effect
            this.clientBound.skip(); // Resource Pack
            this.clientBound.register(Respawn.class);
            this.clientBound.register(HeadRotation.class);
            this.clientBound.skip(); // Update Section Blocks
            this.clientBound.skip(); // Select Advancements Tab
            this.clientBound.skip(); // Server Data
            this.clientBound.register(ActionBar.class);
            this.clientBound.skip(); // Border Center
            this.clientBound.skip(); // Border Lerp Size
            this.clientBound.skip(); // Border Size
            this.clientBound.skip(); // Border Warning Delay
            this.clientBound.skip(); // Border Warning Distance
            this.clientBound.skip(); // Set Camera
            this.clientBound.register(HeldItem.class);
            this.clientBound.register(CenterChunk.class);
            this.clientBound.register(RenderDistance.class);
            this.clientBound.register(SpawnPosition.class);
            this.clientBound.register(DisplayObjective.class);
            this.clientBound.register(EntityMetadata.class);
            this.clientBound.skip(); // Link Entities
            this.clientBound.skip(); // Entity Velocity
            this.clientBound.register(Equipment.class);
            this.clientBound.register(Experience.class);
            this.clientBound.register(Health.class);
            this.clientBound.register(UpdateObjectives.class);
            this.clientBound.skip(); // Set Passengers
            this.clientBound.skip(); // Update Teams
            this.clientBound.register(UpdateScore.class);
            this.clientBound.register(SimulationDistance.class);
            this.clientBound.register(Subtitle.class);
            this.clientBound.skip(); // Update Time
            this.clientBound.register(Title.class);
            this.clientBound.register(TitleAnimationTimes.class);
            this.clientBound.skip(); // Entity Sound Effect
            this.clientBound.skip(); // Sound Effect
            this.clientBound.skip(); // Stop Sound
            this.clientBound.register(SystemChatMessage.class);
            this.clientBound.register(TabListHeaderFooter.class);
            this.clientBound.skip(); // Tag Query Response
            this.clientBound.register(PickupItem.class);
            this.clientBound.skip(); // Teleport Entity
            this.clientBound.skip(); // Update Advancements
            this.clientBound.register(UpdateAttributes.class);
            this.clientBound.register(FeatureFlags.class);
            this.clientBound.skip(); // Entity Effects
            this.clientBound.register(UpdateRecipes.class);
            this.clientBound.register(UpdateTags.class);
        }
    };

    protected final PacketRegistry clientBound = new PacketRegistry();
    protected final PacketRegistry serverBound = new PacketRegistry();

    public static class PacketRegistry {

        protected final IntObjectMap<Supplier<? extends Packet>> packetIdToSupplier = new IntObjectHashMap<>(16, 0.5f);
        protected final Object2IntMap<Class<? extends Packet>> packetClassToId = new Object2IntOpenHashMap<>(16, 0.5f);
        protected int id;

        PacketRegistry() {
            this.packetClassToId.defaultReturnValue(-1);
        }

        protected <P extends Packet> void register(final Class<P> clazz) {
            this.register(clazz, null);
        }

        protected <P extends Packet> void register(final Class<P> clazz,
                                                   final Supplier<P> packetSupplier) {
            final var id = this.id;
            this.packetClassToId.put(clazz, id);
            if (packetSupplier != null) {
                this.packetIdToSupplier.put(id, packetSupplier);
            }
            this.id++;
        }

        public void skip() {
            this.id++;
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
