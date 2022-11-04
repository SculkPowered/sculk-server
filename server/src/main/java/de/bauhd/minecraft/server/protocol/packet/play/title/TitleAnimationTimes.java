package de.bauhd.minecraft.server.protocol.packet.play.title;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class TitleAnimationTimes implements Packet {

    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleAnimationTimes(final int fadeIn, final int stay, final int fadeOut) {
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public TitleAnimationTimes() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeInt(this.fadeIn)
                .writeInt(this.stay)
                .writeInt(this.fadeOut);
    }

    @Override
    public String toString() {
        return "TitleAnimationTimes{" +
                "fadeIn=" + this.fadeIn +
                ", stay=" + this.stay +
                ", fadeOut=" + this.fadeOut +
                '}';
    }
}
