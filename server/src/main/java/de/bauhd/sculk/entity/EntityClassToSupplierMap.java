package de.bauhd.sculk.entity;

import java.util.HashMap;
import java.util.function.Supplier;

public final class EntityClassToSupplierMap extends
    HashMap<Class<? extends Entity>, Supplier<AbstractEntity>> {

  private EntityClassToSupplierMap() {
  }

  public static EntityClassToSupplierMap get() {
    final var map = new EntityClassToSupplierMap();
    map.put(Allay.class, SculkAllay::new);
    map.put(AreaEffectCloud.class, SculkAreaEffectCloud::new);
    map.put(ArmorStand.class, SculkArmorStand::new);
    map.put(Arrow.class, SculkArrow::new);
    map.put(Axolotl.class, SculkAxolotl::new);
    map.put(Bat.class, SculkBat::new);
    map.put(Bee.class, SculkBee::new);
    map.put(Blaze.class, SculkBlaze::new);
    map.put(BlockDisplay.class, SculkBlockDisplay::new);
    map.put(Boat.class, SculkBoat::new);
    map.put(Camel.class, SculkCamel::new);
    map.put(Cat.class, SculkCat::new);
    map.put(CaveSpider.class, SculkCaveSpider::new);
    map.put(ChestBoat.class, SculkChestBoat::new);
    map.put(ChestMinecart.class, SculkChestMinecart::new);
    map.put(Chicken.class, SculkChicken::new);
    map.put(Cod.class, SculkCod::new);
    map.put(CommandBlockMinecart.class, SculkCommandBlockMinecart::new);
    map.put(Cow.class, SculkCow::new);
    map.put(Creeper.class, SculkCreeper::new);
    map.put(Dolphin.class, SculkDolphin::new);
    map.put(Donkey.class, SculkDonkey::new);
    map.put(DragonFireball.class, SculkDragonFireball::new);
    map.put(Drowned.class, SculkDrowned::new);
    map.put(Egg.class, SculkEgg::new);
    map.put(ElderGuardian.class, SculkElderGuardian::new);
    map.put(EndCrystal.class, SculkEndCrystal::new);
    map.put(EnderDragon.class, SculkEnderDragon::new);
    map.put(EnderPearl.class, SculkEnderPearl::new);
    map.put(Enderman.class, SculkEnderman::new);
    map.put(Endermite.class, SculkEndermite::new);
    map.put(Evoker.class, SculkEvoker::new);
    map.put(EvokerFangs.class, SculkEvokerFangs::new);
    map.put(ExperienceBottle.class, SculkExperienceBottle::new);
    map.put(ExperienceOrb.class, SculkExperienceOrb::new);
    map.put(EyeOfEnder.class, SculkEyeOfEnder::new);
    map.put(FallingBlock.class, SculkFallingBlock::new);
    map.put(Fireball.class, SculkFireball::new);
    map.put(FireworkRocket.class, SculkFireworkRocket::new);
    map.put(FishingBobber.class, SculkFishingBobber::new);
    map.put(Fox.class, SculkFox::new);
    map.put(Frog.class, SculkFrog::new);
    map.put(FurnaceMinecart.class, SculkFurnaceMinecart::new);
    map.put(Ghast.class, SculkGhast::new);
    map.put(Giant.class, SculkGiant::new);
    map.put(GlowItemFrame.class, SculkGlowItemFrame::new);
    map.put(GlowSquid.class, SculkGlowSquid::new);
    map.put(Goat.class, SculkGoat::new);
    map.put(Guardian.class, SculkGuardian::new);
    map.put(Hoglin.class, SculkHoglin::new);
    map.put(HopperMinecart.class, SculkHopperMinecart::new);
    map.put(Horse.class, SculkHorse::new);
    map.put(Husk.class, SculkHusk::new);
    map.put(Illusioner.class, SculkIllusioner::new);
    map.put(Interaction.class, SculkInteraction::new);
    map.put(IronGolem.class, SculkIronGolem::new);
    map.put(Item.class, SculkItem::new);
    map.put(ItemDisplay.class, SculkItemDisplay::new);
    map.put(ItemFrame.class, SculkItemFrame::new);
    map.put(LeashKnot.class, SculkLeashKnot::new);
    map.put(LightningBolt.class, SculkLightningBolt::new);
    map.put(Llama.class, SculkLlama::new);
    map.put(LlamaSpit.class, SculkLlamaSpit::new);
    map.put(MagmaCube.class, SculkMagmaCube::new);
    map.put(Minecart.class, SculkMinecart::new);
    map.put(Mooshroom.class, SculkMooshroom::new);
    map.put(Mule.class, SculkMule::new);
    map.put(Ocelot.class, SculkOcelot::new);
    map.put(Painting.class, SculkPainting::new);
    map.put(Panda.class, SculkPanda::new);
    map.put(Parrot.class, SculkParrot::new);
    map.put(Phantom.class, SculkPhantom::new);
    map.put(Pig.class, SculkPig::new);
    map.put(Piglin.class, SculkPiglin::new);
    map.put(PiglinBrute.class, SculkPiglinBrute::new);
    map.put(Pillager.class, SculkPillager::new);
    map.put(PolarBear.class, SculkPolarBear::new);
    map.put(Potion.class, SculkPotion::new);
    map.put(Pufferfish.class, SculkPufferfish::new);
    map.put(Rabbit.class, SculkRabbit::new);
    map.put(Ravager.class, SculkRavager::new);
    map.put(Salmon.class, SculkSalmon::new);
    map.put(Sheep.class, SculkSheep::new);
    map.put(Shulker.class, SculkShulker::new);
    map.put(ShulkerBullet.class, SculkSkulkerBullet::new);
    map.put(Silverfish.class, SculkSilverfish::new);
    map.put(Skeleton.class, SculkSkeleton::new);
    map.put(SkeletonHorse.class, SculkSkeletonHorse::new);
    map.put(Slime.class, SculkSlime::new);
    map.put(SmallFireball.class, SculkSmallFireball::new);
    map.put(Sniffer.class, SculkSniffer::new);
    map.put(SnowGolem.class, SculkSnowGolem::new);
    map.put(Snowball.class, SculkSnowball::new);
    map.put(SpawnerMinecart.class, SculkSpawnerMinecart::new);
    map.put(SpectralArrow.class, SculkSpectralArrow::new);
    map.put(Spider.class, SculkSpider::new);
    map.put(Squid.class, SculkSquid::new);
    map.put(Stray.class, SculkStray::new);
    map.put(Strider.class, SculkStrider::new);
    map.put(Tadpole.class, SculkTadpole::new);
    map.put(TextDisplay.class, SculkTextDisplay::new);
    map.put(Tnt.class, SculkTnt::new);
    map.put(TntMinecart.class, SculkTntMinecart::new);
    map.put(TraderLlama.class, SculkTraderLlama::new);
    map.put(Trident.class, SculkTrident::new);
    map.put(TropicalFish.class, SculkTropicalFish::new);
    map.put(Turtle.class, SculkTurtle::new);
    map.put(Vex.class, SculkVex::new);
    map.put(Villager.class, SculkVillager::new);
    map.put(Vindicator.class, SculkVindicator::new);
    map.put(WanderingTrader.class, SculkWanderingTrader::new);
    map.put(Warden.class, SculkWarden::new);
    map.put(Witch.class, SculkWitch::new);
    map.put(WitherSkeleton.class, SculkWitherSkeleton::new);
    map.put(WitherSkull.class, SculkWitherSkull::new);
    map.put(Wolf.class, SculkWolf::new);
    map.put(Zoglin.class, SculkZoglin::new);
    map.put(Zombie.class, SculkZombie::new);
    map.put(ZombieHorse.class, SculkZombieHorse::new);
    map.put(ZombieVillager.class, SculkZombieVillager::new);
    map.put(ZombifiedPiglin.class, SculkZombifiedPiglin::new);
    return map;
  }
}
