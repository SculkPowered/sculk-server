package de.bauhd.minecraft.server.entity.player;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Session {

     @NotNull UUID sessionId();

     long expiresAt();

     byte[] publicKey();

     byte[] keySignature();

}
