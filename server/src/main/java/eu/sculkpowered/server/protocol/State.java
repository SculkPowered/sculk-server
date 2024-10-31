package eu.sculkpowered.server.protocol;

import eu.sculkpowered.server.entity.TeleportEntityPacket;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.AddEntityPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.AddExperienceOrbPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.AnimatePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.AwardStatsPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BlockChangedAckPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BlockDestructionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BlockEntityDataPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BlockEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BlockUpdatePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BossEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ChangeDifficultyPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ClearTitlesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.CommandsPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ContainerSetContentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ContainerSetDataPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ContainerSetSlotPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.CooldownPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.CustomChatCompletionsPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.CustomQueryPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.DisconnectPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.EntityEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.GameEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LevelChunkWithLightPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginCompressionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginDisconnect;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginFinishedPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.MoveEntityPosPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.MoveEntityPosRotPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.MoveEntityRot;
import eu.sculkpowered.server.protocol.packet.clientbound.OpenScreenPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerAbilitiesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerInfoRemovePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerInfoUpdatePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerPositionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RegistryDataPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RemoveEntitiesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ResetScorePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ResourcePackPopPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ResourcePackPushPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RespawnPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RotateHeadPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetActionBarTextPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetChunkCacheCenterPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetChunkCacheRadiusPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetDefaultSpawnPositionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetDisplayObjectivePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEntityDataPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEntityMotionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEquipmentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetExperiencePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetHealthPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetObjectivePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetPlayerTeamPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetScorePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetSimulationDistancePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetSubtitleTextPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetTimePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetTitleTextPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetTitlesAnimationPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SoundEntityPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SoundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.StatusResponsePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.StopSoundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SystemChatPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.TabListPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.TakeItemEntityPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateAttributesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateEnabledFeaturesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateRecipesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateTagsPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.AcceptTeleportationPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ChatCommandPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ChatPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ChatSessionUpdatePacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ClientCommandPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.CommandSuggestionsPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ContainerButtonClickPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ContainerClickPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.CustomQueryAnswerPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.EditBookPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.HelloPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.Intention;
import eu.sculkpowered.server.protocol.packet.serverbound.InteractPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.KeyPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.LoginAcknowledgedPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.MovePlayerPosPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.MovePlayerPosRotPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.MovePlayerRotPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.MovePlayerStatusOnlyPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.PlayerActionPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.PlayerCommandPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.RecipeBookChangeSettingsPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.SetCreativeModeSlotPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.StatusRequest;
import eu.sculkpowered.server.protocol.packet.serverbound.SwingPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.TeleportToEntityPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.UseItemOnPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.UseItemPacket;
import eu.sculkpowered.server.protocol.packet.shared.CarriedItemPacket;
import eu.sculkpowered.server.protocol.packet.shared.ContainerClosePacket;
import eu.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;
import eu.sculkpowered.server.protocol.packet.shared.FinishConfigurationPacket;
import eu.sculkpowered.server.protocol.packet.shared.KeepAlivePacket;
import eu.sculkpowered.server.protocol.packet.shared.StatusPingPacket;
import io.netty.handler.codec.EncoderException;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.function.Supplier;

public enum State {

  HANDSHAKE(
      clientBound(),
      serverBound()
          .register(Intention::new)
  ),
  STATUS(
      clientBound()
          .register(StatusResponsePacket.class)
          .register(StatusPingPacket.class),
      serverBound()
          .register(() -> StatusRequest.INSTANCE)
          .register(StatusPingPacket::new)
  ),
  LOGIN(
      clientBound()
          .register(LoginDisconnect.class)
          .register(eu.sculkpowered.server.protocol.packet.clientbound.HelloPacket.class)
          .register(LoginFinishedPacket.class)
          .register(LoginCompressionPacket.class)
          .register(CustomQueryPacket.class)
          .skip(), // Cookie Request
      serverBound().register(HelloPacket::new)
          .register(KeyPacket::new)
          .register(CustomQueryAnswerPacket::new)
          .register(() -> LoginAcknowledgedPacket.INSTANCE)
          .skip() // Cookie Response
  ),
  CONFIG(
      clientBound()
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
          .skip(), // Server Links,
      serverBound()
          .register(ClientInformationPacket.SUPPLIER)
          .skip() // Cookie Response
          .register(CustomPayloadPacket.SUPPLIER)
          .register(() -> FinishConfigurationPacket.INSTANCE)
          .register(KeepAlivePacket.SUPPLIER)
          .skip() // Pong
          .skip() // Resource Pack
          .skip() // Select Known Packs
  ),
  PLAY(
      clientBound()
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
              eu.sculkpowered.server.protocol.packet.clientbound.CommandSuggestionsPacket.class)
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
          .skip(), // Server Links
      serverBound()
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
          .register(UseItemPacket::new)
  );

  public final ClientboundRegistry clientBound;
  public final ServerboundRegistry serverBound;

  State(final ClientboundRegistry clientBound, final ServerboundRegistry serverBound) {
    this.clientBound = clientBound;
    this.serverBound = serverBound;
  }

  public static ClientboundRegistry clientBound() {
    return new ClientboundRegistry();
  }

  public static ServerboundRegistry serverBound() {
    return new ServerboundRegistry();
  }

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

    protected <P extends ServerboundPacket> ServerboundRegistry register(
        final Supplier<P> packetSupplier) {
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
