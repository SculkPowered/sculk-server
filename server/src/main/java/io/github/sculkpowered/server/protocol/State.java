package io.github.sculkpowered.server.protocol;

import io.github.sculkpowered.server.entity.TeleportEntity;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.config.FeatureFlags;
import io.github.sculkpowered.server.protocol.packet.config.FinishConfiguration;
import io.github.sculkpowered.server.protocol.packet.config.RegistryData;
import io.github.sculkpowered.server.protocol.packet.handshake.Handshake;
import io.github.sculkpowered.server.protocol.packet.login.CompressionPacket;
import io.github.sculkpowered.server.protocol.packet.login.Disconnect;
import io.github.sculkpowered.server.protocol.packet.login.EncryptionRequest;
import io.github.sculkpowered.server.protocol.packet.login.EncryptionResponse;
import io.github.sculkpowered.server.protocol.packet.login.LoginAcknowledged;
import io.github.sculkpowered.server.protocol.packet.login.LoginPluginRequest;
import io.github.sculkpowered.server.protocol.packet.login.LoginPluginResponse;
import io.github.sculkpowered.server.protocol.packet.login.LoginStart;
import io.github.sculkpowered.server.protocol.packet.login.LoginSuccess;
import io.github.sculkpowered.server.protocol.packet.play.ActionBar;
import io.github.sculkpowered.server.protocol.packet.play.AwardStatistics;
import io.github.sculkpowered.server.protocol.packet.play.BossBar;
import io.github.sculkpowered.server.protocol.packet.play.ChatSuggestions;
import io.github.sculkpowered.server.protocol.packet.play.ChangeDifficulty;
import io.github.sculkpowered.server.protocol.packet.play.ChatMessage;
import io.github.sculkpowered.server.protocol.packet.play.ClientCommand;
import io.github.sculkpowered.server.protocol.packet.play.ClientInformation;
import io.github.sculkpowered.server.protocol.packet.play.ConfirmTeleportation;
import io.github.sculkpowered.server.protocol.packet.play.Cooldown;
import io.github.sculkpowered.server.protocol.packet.play.CreativeModeSlot;
import io.github.sculkpowered.server.protocol.packet.play.EditBook;
import io.github.sculkpowered.server.protocol.packet.play.EntityAnimation;
import io.github.sculkpowered.server.protocol.packet.play.EntityEvent;
import io.github.sculkpowered.server.protocol.packet.play.EntityMetadata;
import io.github.sculkpowered.server.protocol.packet.play.EntityVelocity;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.Experience;
import io.github.sculkpowered.server.protocol.packet.play.GameEvent;
import io.github.sculkpowered.server.protocol.packet.play.Health;
import io.github.sculkpowered.server.protocol.packet.play.HeldItem;
import io.github.sculkpowered.server.protocol.packet.play.Interact;
import io.github.sculkpowered.server.protocol.packet.play.KeepAlive;
import io.github.sculkpowered.server.protocol.packet.play.Login;
import io.github.sculkpowered.server.protocol.packet.play.PickupItem;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAbilities;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAction;
import io.github.sculkpowered.server.protocol.packet.play.PlayerCommand;
import io.github.sculkpowered.server.protocol.packet.play.PlayerInfo;
import io.github.sculkpowered.server.protocol.packet.play.PlayerInfoRemove;
import io.github.sculkpowered.server.protocol.packet.play.PlayerSession;
import io.github.sculkpowered.server.protocol.packet.play.PluginMessage;
import io.github.sculkpowered.server.protocol.packet.play.RecipeBookSettings;
import io.github.sculkpowered.server.protocol.packet.play.RemoveEntities;
import io.github.sculkpowered.server.protocol.packet.play.RenderDistance;
import io.github.sculkpowered.server.protocol.packet.play.Respawn;
import io.github.sculkpowered.server.protocol.packet.play.SimulationDistance;
import io.github.sculkpowered.server.protocol.packet.play.SpawnEntity;
import io.github.sculkpowered.server.protocol.packet.play.SpawnExperienceOrb;
import io.github.sculkpowered.server.protocol.packet.play.SpawnPosition;
import io.github.sculkpowered.server.protocol.packet.play.SwingArm;
import io.github.sculkpowered.server.protocol.packet.play.SynchronizePlayerPosition;
import io.github.sculkpowered.server.protocol.packet.play.SystemChatMessage;
import io.github.sculkpowered.server.protocol.packet.play.TabListHeaderFooter;
import io.github.sculkpowered.server.protocol.packet.play.TeleportToEntity;
import io.github.sculkpowered.server.protocol.packet.play.UpdateAttributes;
import io.github.sculkpowered.server.protocol.packet.play.UpdateRecipes;
import io.github.sculkpowered.server.protocol.packet.play.UpdateTags;
import io.github.sculkpowered.server.protocol.packet.play.UpdateTeams;
import io.github.sculkpowered.server.protocol.packet.play.UseItem;
import io.github.sculkpowered.server.protocol.packet.play.UseItemOn;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockAcknowledge;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockAction;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockEntityData;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockUpdate;
import io.github.sculkpowered.server.protocol.packet.play.chunk.CenterChunk;
import io.github.sculkpowered.server.protocol.packet.play.chunk.ChunkDataAndUpdateLight;
import io.github.sculkpowered.server.protocol.packet.play.command.ChatCommand;
import io.github.sculkpowered.server.protocol.packet.play.command.CommandSuggestionsRequest;
import io.github.sculkpowered.server.protocol.packet.play.command.CommandSuggestionsResponse;
import io.github.sculkpowered.server.protocol.packet.play.command.Commands;
import io.github.sculkpowered.server.protocol.packet.play.container.ClickContainer;
import io.github.sculkpowered.server.protocol.packet.play.container.ClickContainerButton;
import io.github.sculkpowered.server.protocol.packet.play.container.CloseContainer;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerContent;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerProperty;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerSlot;
import io.github.sculkpowered.server.protocol.packet.play.container.OpenScreen;
import io.github.sculkpowered.server.protocol.packet.play.position.EntityPosition;
import io.github.sculkpowered.server.protocol.packet.play.position.EntityPositionAndRotation;
import io.github.sculkpowered.server.protocol.packet.play.position.EntityRotation;
import io.github.sculkpowered.server.protocol.packet.play.position.HeadRotation;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerOnGround;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerPosition;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerPositionAndRotation;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerRotation;
import io.github.sculkpowered.server.protocol.packet.play.scoreboard.DisplayObjective;
import io.github.sculkpowered.server.protocol.packet.play.scoreboard.UpdateObjectives;
import io.github.sculkpowered.server.protocol.packet.play.scoreboard.UpdateScore;
import io.github.sculkpowered.server.protocol.packet.play.sound.EntitySoundEffect;
import io.github.sculkpowered.server.protocol.packet.play.sound.SoundEffect;
import io.github.sculkpowered.server.protocol.packet.play.sound.StopSound;
import io.github.sculkpowered.server.protocol.packet.play.title.ClearTitles;
import io.github.sculkpowered.server.protocol.packet.play.title.Subtitle;
import io.github.sculkpowered.server.protocol.packet.play.title.Title;
import io.github.sculkpowered.server.protocol.packet.play.title.TitleAnimationTimes;
import io.github.sculkpowered.server.protocol.packet.status.StatusPing;
import io.github.sculkpowered.server.protocol.packet.status.StatusRequest;
import io.github.sculkpowered.server.protocol.packet.status.StatusResponse;
import io.netty.handler.codec.EncoderException;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.function.Supplier;

public enum State {

  HANDSHAKE {
    {
      this.serverBound
          .register(Handshake::new);
    }
  },
  STATUS {
    {
      this.serverBound
          .register(() -> StatusRequest.INSTANCE)
          .register(StatusPing::new);

      this.clientBound
          .register(StatusResponse.class)
          .register(StatusPing.class);
    }
  },
  LOGIN {
    {
      this.serverBound
          .register(LoginStart::new)
          .register(EncryptionResponse::new)
          .register(LoginPluginResponse::new)
          .register(() -> LoginAcknowledged.INSTANCE);

      this.clientBound
          .register(Disconnect.class)
          .register(EncryptionRequest.class)
          .register(LoginSuccess.class)
          .register(CompressionPacket.class)
          .register(LoginPluginRequest.class);
    }
  },
  CONFIG {
    {
      this.serverBound
          .register(ClientInformation.SUPPLIER)
          .register(PluginMessage.SUPPLIER)
          .register(() -> FinishConfiguration.INSTANCE)
          .register(KeepAlive.SUPPLIER);
      // Pong
      // Resource Pack

      this.clientBound
          .register(PluginMessage.class)
          .register(Disconnect.class)
          .register(FinishConfiguration.class)
          .register(KeepAlive.class)
          .skip() // Ping
          .register(RegistryData.class)
          .skip() // Resource Pack
          .register(FeatureFlags.class);
      // Update Tags
    }
  },
  PLAY {
    {
      this.serverBound
          .register(ConfirmTeleportation::new)
          .skip() // Query Block Entity Tag
          .skip() // Change Difficulty - only Single-player
          .skip() // Message Acknowledgment
          .register(ChatCommand::new)
          .register(ChatMessage::new)
          .register(PlayerSession::new) // Player Session
          .skip() // Chunk Batch Received
          .register(ClientCommand::new)
          .register(ClientInformation.SUPPLIER)
          .register(CommandSuggestionsRequest::new)
          .skip() // Configuration Acknowledged
          .register(ClickContainerButton::new)
          .register(ClickContainer::new)
          .register(CloseContainer::new)
          .register(PluginMessage.SUPPLIER)
          .register(EditBook::new)
          .skip() // Query Entity Tag
          .register(Interact::new)
          .skip() // Jigsaw Generate
          .register(KeepAlive.SUPPLIER)
          .skip() // Lock Difficult
          .register(PlayerPosition::new)
          .register(PlayerPositionAndRotation::new)
          .register(PlayerRotation::new)
          .register(PlayerOnGround::new)
          .skip() // Move Vehicle
          .skip() // Paddle Boat
          .skip() // Pick Item
          .skip() // Ping Request
          .skip() // Place Recipe
          .register(PlayerAbilities::new)
          .register(PlayerAction::new)
          .register(PlayerCommand::new)
          .skip() // Player Input
          .skip() // Pong
          .register(RecipeBookSettings::new)
          .skip() // Seen Recipe
          .skip() // Rename Item
          .skip() // Resource Pack
          .skip() // Advancements
          .skip() // Select Trade
          .skip() // Beacon Effect
          .register(HeldItem::new)
          .skip() // Program Command Block
          .skip() // Program Command Block Minecart
          .register(CreativeModeSlot::new)
          .skip() // Program Jigsaw Block
          .skip() // Program Structure Block
          .skip() // Update Sign
          .register(SwingArm::new)
          .register(TeleportToEntity::new)
          .register(UseItemOn::new)
          .register(UseItem::new);

      this.clientBound
          .skip() // Bundle Delimiter
          .register(SpawnEntity.class)
          .register(SpawnExperienceOrb.class)
          .register(EntityAnimation.class)
          .register(AwardStatistics.class)
          .register(BlockAcknowledge.class)
          .skip() // Block Destroy Stage
          .register(BlockEntityData.class)
          .register(BlockAction.class)
          .register(BlockUpdate.class)
          .register(BossBar.class)
          .register(ChangeDifficulty.class)
          .skip() // Chunk Batch Finished
          .skip() // Chunk Batch Start
          .skip() // Chunk Biomes
          .register(ClearTitles.class)
          .register(CommandSuggestionsResponse.class)
          .register(Commands.class)
          .register(CloseContainer.class)
          .register(ContainerContent.class)
          .register(ContainerProperty.class)
          .register(ContainerSlot.class)
          .register(Cooldown.class)
          .register(ChatSuggestions.class)
          .register(PluginMessage.class)
          .skip() // Damage Event
          .skip() // Delete Message
          .register(Disconnect.class)
          .skip() // Disguised Chat Message
          .register(EntityEvent.class)
          .skip() // Explosion
          .skip() // Unload Chunk
          .register(GameEvent.class)
          .skip() // Open Horse Screen
          .skip() // Hurt Animation
          .skip() // Initialize World Border
          .register(KeepAlive.class)
          .register(ChunkDataAndUpdateLight.class)
          .skip() // World Event
          .skip() // Particle
          .skip() // Update Light
          .register(Login.class)
          .skip() // Map Data
          .skip() // Merchant Offers
          .register(EntityPosition.class)
          .register(EntityPositionAndRotation.class)
          .register(EntityRotation.class)
          .skip() // Move Vehicle
          .skip() // Open Book
          .register(OpenScreen.class)
          .skip() // Open Sign Editor
          .skip() // Ping
          .skip() // Ping Response
          .skip() // Place Ghost Recipe
          .register(PlayerAbilities.class)
          .skip() // Player Chat Message
          .skip() // End Combat - was once used for Twitch
          .skip() // Enter Combat - was once used for Twitch
          .skip() // Combat Death
          .register(PlayerInfoRemove.class)
          .register(PlayerInfo.class)
          .skip() // Look At
          .register(SynchronizePlayerPosition.class)
          .skip() // Update Recipe Book
          .register(RemoveEntities.class)
          .skip() // Remove Entity Effect
          .skip() // Resource Pack
          .register(Respawn.class)
          .register(HeadRotation.class)
          .skip() // Update Section Blocks
          .skip() // Select Advancements Tab
          .skip() // Server Data
          .register(ActionBar.class)
          .skip() // Border Center
          .skip() // Border Lerp Size
          .skip() // Border Size
          .skip() // Border Warning Delay
          .skip() // Border Warning Distance
          .skip() // Set Camera
          .register(HeldItem.class)
          .register(CenterChunk.class)
          .register(RenderDistance.class)
          .register(SpawnPosition.class)
          .register(DisplayObjective.class)
          .register(EntityMetadata.class)
          .skip() // Link Entities
          .register(EntityVelocity.class)
          .register(Equipment.class)
          .register(Experience.class)
          .register(Health.class)
          .register(UpdateObjectives.class)
          .skip() // Set Passengers
          .register(UpdateTeams.class)
          .register(UpdateScore.class)
          .register(SimulationDistance.class)
          .register(Subtitle.class)
          .skip() // Update Time
          .register(Title.class)
          .register(TitleAnimationTimes.class)
          .register(EntitySoundEffect.class)
          .register(SoundEffect.class)
          .skip() // Start Configuration
          .register(StopSound.class)
          .register(SystemChatMessage.class)
          .register(TabListHeaderFooter.class)
          .skip() // Tag Query Response
          .register(PickupItem.class)
          .register(TeleportEntity.class)
          .skip() // Update Advancements
          .register(UpdateAttributes.class)
          .skip() // Entity Effects
          .register(UpdateRecipes.class)
          .register(UpdateTags.class);
    }
  };

  public final ClientboundRegistry clientBound = new ClientboundRegistry();
  public final ServerboundRegistry serverBound = new ServerboundRegistry();

  public static class ClientboundRegistry {

    protected final Object2IntMap<Class<? extends Packet>> packetClassToId =
        new Object2IntOpenHashMap<>(16, 0.5f);
    protected int id;

    ClientboundRegistry() {
      this.packetClassToId.defaultReturnValue(-1);
    }

    protected <P extends Packet> ClientboundRegistry register(final Class<P> clazz) {
      final var id = this.id;
      this.packetClassToId.put(clazz, id);
      this.id++;
      return this;
    }

    public ClientboundRegistry skip() {
      this.id++;
      return this;
    }

    public int packetId(final Packet packet) {
      final var id = this.packetClassToId.getInt(packet.getClass());
      if (id == Integer.MIN_VALUE) {
        throw new EncoderException("Couldn't find an id for " + packet.getClass());
      }
      return id;
    }
  }

  public static class ServerboundRegistry {

    protected final IntObjectMap<Supplier<? extends Packet>> packetIdToSupplier =
        new IntObjectHashMap<>(16, 0.5f);
    protected int id;

    protected <P extends Packet> ServerboundRegistry register(final Supplier<P> packetSupplier) {
      final var id = this.id;
      if (packetSupplier != null) {
        this.packetIdToSupplier.put(id, packetSupplier);
      }
      this.id++;
      return this;
    }

    public ServerboundRegistry skip() {
      this.id++;
      return this;
    }

    public Packet createPacket(final int id) {
      final var supplier = this.packetIdToSupplier.get(id);
      if (supplier != null) {
        return supplier.get();
      }
      return null;
    }
  }
}
