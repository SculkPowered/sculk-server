package io.github.sculkpowered.server.protocol;

import io.github.sculkpowered.server.entity.TeleportEntityPacket;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.BossEventPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ChangeDifficultyPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ClearTitlesPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.UpdateEnabledFeaturesPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.BlockDestructionPacket;
import io.github.sculkpowered.server.protocol.packet.shared.FinishConfigurationPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.RegistryDataPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.Intention;
import io.github.sculkpowered.server.protocol.packet.clientbound.LoginCompressionPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.HelloPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.KeyPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.LoginAcknowledgedPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.LoginDisconnect;
import io.github.sculkpowered.server.protocol.packet.clientbound.CustomQueryPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.CustomQueryAnswerPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.GameProfilePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetActionBarTextPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ResourcePackPushPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.AwardStatsPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.CustomChatCompletionsPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ChatPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ClientCommandPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.AcceptTeleportationPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.CooldownPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.SetCreativeModeSlotPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.DisconnectPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.EditBookPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.AnimatePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.EntityEventPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetEntityDataPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetEntityMotionPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetEquipmentPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetExperiencePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.GameEventPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetHealthPacket;
import io.github.sculkpowered.server.protocol.packet.shared.CarriedItemPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.InteractPacket;
import io.github.sculkpowered.server.protocol.packet.shared.KeepAlivePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.LoginPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.TakeItemEntityPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.PlayerAbilitiesPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.PlayerActionPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.PlayerCommandPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.PlayerInfoUpdatePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.PlayerInfoRemovePacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ChatSessionUpdatePacket;
import io.github.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.RecipeBookChangeSettingsPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.RemoveEntitiesPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ResourcePackPopPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetChunkCacheRadiusPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.RespawnPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetSimulationDistancePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.AddEntityPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.AddExperienceOrbPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetDefaultSpawnPositionPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.SwingPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.PlayerPositionPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SystemChatPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.TabListPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.TeleportToEntityPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.UpdateAttributesPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.UpdateRecipesPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.UpdateTagsPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetPlayerTeamPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetTimePacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.UseItemPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.UseItemOnPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.BlockChangedAckPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.BlockEventPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.BlockEntityDataPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.BlockUpdatePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetChunkCacheCenterPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.LevelChunkWithLightPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ChatCommandPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.CommandSuggestionsPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.CommandsPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ContainerClickPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ContainerButtonClickPacket;
import io.github.sculkpowered.server.protocol.packet.shared.ContainerClosePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ContainerSetContentPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ContainerSetDataPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ContainerSetSlotPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.OpenScreenPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.MoveEntityPosPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.MoveEntityPosRotPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.MoveEntityRot;
import io.github.sculkpowered.server.protocol.packet.clientbound.RotateHeadPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerStatusOnlyPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerPosPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerPosRotPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerRotPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetDisplayObjectivePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.ResetScorePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetObjectivePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetScorePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SoundEntityPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SoundPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.StopSoundPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetSubtitleTextPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetTitleTextPacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.SetTitlesAnimationPacket;
import io.github.sculkpowered.server.protocol.packet.shared.StatusPingPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.StatusRequest;
import io.github.sculkpowered.server.protocol.packet.clientbound.StatusResponsePacket;
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
          .register(Intention::new);
    }
  },
  STATUS {
    {
      this.serverBound
          .register(() -> StatusRequest.INSTANCE)
          .register(StatusPingPacket::new);

      this.clientBound
          .register(StatusResponsePacket.class)
          .register(StatusPingPacket.class);
    }
  },
  LOGIN {
    {
      this.serverBound
          .register(io.github.sculkpowered.server.protocol.packet.serverbound.HelloPacket::new)
          .register(KeyPacket::new)
          .register(CustomQueryAnswerPacket::new)
          .register(() -> LoginAcknowledgedPacket.INSTANCE)
          .skip(); // Cookie Response

      this.clientBound
          .register(LoginDisconnect.class)
          .register(HelloPacket.class)
          .register(GameProfilePacket.class)
          .register(LoginCompressionPacket.class)
          .register(CustomQueryPacket.class)
          .skip(); // Cookie Request
    }
  },
  CONFIG {
    {
      this.serverBound
          .register(ClientInformationPacket.SUPPLIER)
          .skip() // Cookie Response
          .register(CustomPayloadPacket.SUPPLIER)
          .register(() -> FinishConfigurationPacket.INSTANCE)
          .register(KeepAlivePacket.SUPPLIER)
          .skip() // Pong
          .skip() // Resource Pack
          .skip(); // Select Known Packs

      this.clientBound
          .skip() // Cookie Request
          .register(CustomPayloadPacket.class)
          .register(DisconnectPacket.class)
          .register(FinishConfigurationPacket.class)
          .register(KeepAlivePacket.class)
          .skip() // Ping
          .skip() // Reset Chat
          .register(RegistryDataPacket.class)
          .register(ResourcePackPopPacket.class)
          .register(ResourcePackPushPacket.class)
          .skip() // Store Cookie
          .skip() // Transfer
          .register(UpdateEnabledFeaturesPacket.class)
          .register(UpdateTagsPacket.class)
          .skip() // Select Known Packs
          .skip() // Custom Report Details
          .skip(); // Server Links
    }
  },
  PLAY {
    {
      this.serverBound
          .register(AcceptTeleportationPacket::new)
          .skip() // Block Entity Tag Query
          .skip() // Change Difficulty - only Single-player
          .skip() // Chat Ack
          .register(ChatCommandPacket::new)
          .skip() // Chat Command Signed
          .register(ChatPacket::new)
          .register(ChatSessionUpdatePacket::new)
          .skip() // Chunk Batch Received
          .register(ClientCommandPacket::new)
          .register(ClientInformationPacket.SUPPLIER)
          .register(CommandSuggestionsPacket::new)
          .skip() // Configuration Acknowledged
          .register(ContainerButtonClickPacket::new)
          .register(ContainerClickPacket::new)
          .register(ContainerClosePacket::new)
          .skip() // Container Slot State Changed
          .skip() // Cookie Response
          .register(CustomPayloadPacket.SUPPLIER)
          .skip() // Debug Sample Subscription
          .register(EditBookPacket::new)
          .skip() // Entity Tag Query
          .register(InteractPacket::new)
          .skip() // Jigsaw Generate
          .register(KeepAlivePacket.SUPPLIER)
          .skip() // Lock Difficulty
          .register(MovePlayerPosPacket::new)
          .register(MovePlayerPosRotPacket::new)
          .register(MovePlayerRotPacket::new)
          .register(MovePlayerStatusOnlyPacket::new)
          .skip() // Move Vehicle
          .skip() // Paddle Boat
          .skip() // Pick Item
          .skip() // Ping Request
          .skip() // Place Recipe
          .skip() // Player Abilities
          .register(PlayerActionPacket::new)
          .register(PlayerCommandPacket::new)
          .skip() // Player Input
          .skip() // Pong
          .register(RecipeBookChangeSettingsPacket::new)
          .skip() // Recipe Book Seen Recipe
          .skip() // Rename Item
          .skip() // Resource Pack
          .skip() // Seen Advancements
          .skip() // Select Trade
          .skip() // Set Beacon
          .register(CarriedItemPacket::new)
          .skip() // Set Command Block
          .skip() // Set Command Minecart
          .register(SetCreativeModeSlotPacket::new)
          .skip() // Set Jigsaw Block
          .skip() // Set Structure Block
          .skip() // Sign Update
          .register(SwingPacket::new)
          .register(TeleportToEntityPacket::new)
          .register(UseItemOnPacket::new)
          .register(UseItemPacket::new);

      this.clientBound
          .skip() // Bundle Delimiter
          .register(AddEntityPacket.class)
          .register(AddExperienceOrbPacket.class)
          .register(AnimatePacket.class)
          .register(AwardStatsPacket.class)
          .register(BlockChangedAckPacket.class)
          .register(BlockDestructionPacket.class)
          .register(BlockEntityDataPacket.class)
          .register(BlockEventPacket.class)
          .register(BlockUpdatePacket.class)
          .register(BossEventPacket.class)
          .register(ChangeDifficultyPacket.class)
          .skip() // Chunk Batch Finished
          .skip() // Chunk Batch Start
          .skip() // Chunk Biomes
          .register(ClearTitlesPacket.class)
          .register(
              io.github.sculkpowered.server.protocol.packet.clientbound.CommandSuggestionsPacket.class)
          .register(CommandsPacket.class)
          .register(ContainerClosePacket.class)
          .register(ContainerSetContentPacket.class)
          .register(ContainerSetDataPacket.class)
          .register(ContainerSetSlotPacket.class)
          .skip() // Cookie Request
          .register(CooldownPacket.class)
          .register(CustomChatCompletionsPacket.class)
          .register(CustomPayloadPacket.class)
          .skip() // Damage Event
          .skip() // Debug Sample
          .skip() // Delete Chat
          .register(DisconnectPacket.class)
          .skip() // Disguised Chat
          .register(EntityEventPacket.class)
          .skip() // Explode
          .skip() // Forget Level Chunk
          .register(GameEventPacket.class)
          .skip() // Horse Screen Open
          .skip() // Hurt Animation
          .skip() // Initialize Border
          .register(KeepAlivePacket.class)
          .register(LevelChunkWithLightPacket.class)
          .skip() // Level Event
          .skip() // Level Particles
          .skip() // Light Update
          .register(LoginPacket.class)
          .skip() // Map Item Data
          .skip() // Merchant Offers
          .register(MoveEntityPosPacket.class)
          .register(MoveEntityPosRotPacket.class)
          .register(MoveEntityRot.class)
          .skip() // Move Vehicle
          .skip() // Open Book
          .register(OpenScreenPacket.class)
          .skip() // Open Sign Editor
          .skip() // Ping
          .skip() // Pong Response
          .skip() // Place Ghost Recipe
          .register(PlayerAbilitiesPacket.class)
          .skip() // Player Chat
          .skip() // Player Combat End - was once used for Twitch
          .skip() // Player Combat Enter - was once used for Twitch
          .skip() // Player Combat Kill
          .register(PlayerInfoRemovePacket.class)
          .register(PlayerInfoUpdatePacket.class)
          .skip() // Player Look At
          .register(PlayerPositionPacket.class)
          .skip() // Recipe
          .register(RemoveEntitiesPacket.class)
          .skip() // Remove Mob Effect
          .register(ResetScorePacket.class)
          .register(ResourcePackPopPacket.class)
          .register(ResourcePackPushPacket.class)
          .register(RespawnPacket.class)
          .register(RotateHeadPacket.class)
          .skip() // Section Blocks Update
          .skip() // Select Advancements Tab
          .skip() // Server Data
          .register(SetActionBarTextPacket.class)
          .skip() // Set Border Center
          .skip() // Set Border Lerp Size
          .skip() // Set Border Size
          .skip() // Set Border Warning Delay
          .skip() // Set Border Warning Distance
          .skip() // Set Camera
          .register(CarriedItemPacket.class)
          .register(SetChunkCacheCenterPacket.class)
          .register(SetChunkCacheRadiusPacket.class)
          .register(SetDefaultSpawnPositionPacket.class)
          .register(SetDisplayObjectivePacket.class)
          .register(SetEntityDataPacket.class)
          .skip() // Set Entity Link
          .register(SetEntityMotionPacket.class)
          .register(SetEquipmentPacket.class)
          .register(SetExperiencePacket.class)
          .register(SetHealthPacket.class)
          .register(SetObjectivePacket.class)
          .skip() // Set Passengers
          .register(SetPlayerTeamPacket.class)
          .register(SetScorePacket.class)
          .register(SetSimulationDistancePacket.class)
          .register(SetSubtitleTextPacket.class)
          .register(SetTimePacket.class)
          .register(SetTitleTextPacket.class)
          .register(SetTitlesAnimationPacket.class)
          .register(SoundEntityPacket.class)
          .register(SoundPacket.class)
          .skip() // Start Configuration
          .register(StopSoundPacket.class)
          .skip() // Store Cookie
          .register(SystemChatPacket.class)
          .register(TabListPacket.class)
          .skip() // Tag Query
          .register(TakeItemEntityPacket.class)
          .register(TeleportEntityPacket.class)
          .skip() // Ticking State
          .skip() // Ticking Step
          .skip() // Transfer
          .skip() // Update Advancements
          .register(UpdateAttributesPacket.class)
          .skip() // Update Mob Effect
          .register(UpdateRecipesPacket.class) // TODO
          .register(UpdateTagsPacket.class) // TODO
          .skip() // Projectile Power
          .skip() // Custom Report Details
          .skip(); // Server Links
    }
  };

  public final ClientboundRegistry clientBound = new ClientboundRegistry();
  public final ServerboundRegistry serverBound = new ServerboundRegistry();

  public static class ClientboundRegistry {

    protected final Object2IntMap<Class<? extends ClientboundPacket>> packetClassToId =
        new Object2IntOpenHashMap<>(16, 0.5f);
    protected int id;

    ClientboundRegistry() {
      this.packetClassToId.defaultReturnValue(-1);
    }

    protected <P extends ClientboundPacket> ClientboundRegistry register(final Class<P> clazz) {
      final var id = this.id;
      this.packetClassToId.put(clazz, id);
      this.id++;
      return this;
    }

    public ClientboundRegistry skip() {
      this.id++;
      return this;
    }

    public int packetId(final ClientboundPacket packet) {
      final var id = this.packetClassToId.getInt(packet.getClass());
      if (id == Integer.MIN_VALUE) {
        throw new EncoderException("Couldn't find an id for " + packet.getClass());
      }
      return id;
    }
  }

  public static class ServerboundRegistry {

    protected final IntObjectMap<Supplier<? extends ServerboundPacket>> packetIdToSupplier =
        new IntObjectHashMap<>(16, 0.5f);
    protected int id;

    protected <P extends ServerboundPacket> ServerboundRegistry register(final Supplier<P> packetSupplier) {
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

    public ServerboundPacket createPacket(final int id) {
      final var supplier = this.packetIdToSupplier.get(id);
      if (supplier != null) {
        return supplier.get();
      }
      return null;
    }
  }
}
