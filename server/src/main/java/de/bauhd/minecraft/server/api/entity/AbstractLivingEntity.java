package de.bauhd.minecraft.server.api.entity;

public abstract class AbstractLivingEntity extends AbstractEntity implements LivingEntity {

    @Override
    public float getHealth() {
        return 0;
    }

    @Override
    public void setHealth(float health) {

    }
}
