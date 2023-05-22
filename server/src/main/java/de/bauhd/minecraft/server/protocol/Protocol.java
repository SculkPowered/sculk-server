package de.bauhd.minecraft.server.protocol;

import java.util.HashMap;
import java.util.Map;

public final class Protocol {

    public enum Version {
        UNKNOWN(-1, "Unknown"),
        LEGACY(-2, "Legacy"),
        MINECRAFT_1_7_2(4,
                "1.7.2", "1.7.3", "1.7.4", "1.7.5"),
        MINECRAFT_1_7_6(5,
                "1.7.6", "1.7.7", "1.7.8", "1.7.9", "1.7.10"),
        MINECRAFT_1_8(47,
                "1.8", "1.8.1", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9"),
        MINECRAFT_1_9(107, "1.9"),
        MINECRAFT_1_9_1(108, "1.9.1"),
        MINECRAFT_1_9_2(109, "1.9.2"),
        MINECRAFT_1_9_4(110, "1.9.3", "1.9.4"),
        MINECRAFT_1_10(210, "1.10", "1.10.1", "1.10.2"),
        MINECRAFT_1_11(315, "1.11"),
        MINECRAFT_1_11_1(316, "1.11.1", "1.11.2"),
        MINECRAFT_1_12(335, "1.12"),
        MINECRAFT_1_12_1(338, "1.12.1"),
        MINECRAFT_1_12_2(340, "1.12.2"),
        MINECRAFT_1_13(393, "1.13"),
        MINECRAFT_1_13_1(401, "1.13.1"),
        MINECRAFT_1_13_2(404, "1.13.2"),
        MINECRAFT_1_14(477, "1.14"),
        MINECRAFT_1_14_1(480, "1.14.1"),
        MINECRAFT_1_14_2(485, "1.14.2"),
        MINECRAFT_1_14_3(490, "1.14.3"),
        MINECRAFT_1_14_4(498, "1.14.4"),
        MINECRAFT_1_15(573, "1.15"),
        MINECRAFT_1_15_1(575, "1.15.1"),
        MINECRAFT_1_15_2(578, "1.15.2"),
        MINECRAFT_1_16(735, "1.16"),
        MINECRAFT_1_16_1(736, "1.16.1"),
        MINECRAFT_1_16_2(751, "1.16.2"),
        MINECRAFT_1_16_3(753, "1.16.3"),
        MINECRAFT_1_16_4(754, "1.16.4", "1.16.5"),
        MINECRAFT_1_17(755, "1.17"),
        MINECRAFT_1_17_1(756, "1.17.1"),
        MINECRAFT_1_18(757, "1.18", "1.18.1"),
        MINECRAFT_1_18_2(758, "1.18.2"),
        MINECRAFT_1_19(759, "1.19"),
        MINECRAFT_1_19_1(760, "1.19.1", "1.19.2"),
        MINECRAFT_1_19_3(761, "1.19.3"),
        MINECRAFT_1_19_4(762, "1.19.4");

        public static final Version CURRENT_VERSION = MINECRAFT_1_19_4;

        private static final Map<Integer, Version> PROTOCOL_VERSIONS;

        public static final String SUPPORTED_VERSIONS = CURRENT_VERSION.versions[0]
                + "-" + CURRENT_VERSION.versions[CURRENT_VERSION.versions.length - 1];

        static {
            PROTOCOL_VERSIONS = new HashMap<>();
            for (final var version : values()) {
                PROTOCOL_VERSIONS.putIfAbsent(version.protocolId, version);
            }
        }

        public static Version get(final int protocolVersion) {
            return PROTOCOL_VERSIONS.getOrDefault(protocolVersion, Version.UNKNOWN);
        }

        private final int protocolId;
        private final String[] versions;

        Version(final int protocolId, final String... versions) {
            this.protocolId = protocolId;
            this.versions = versions;
        }

        public int protocolId() {
            return this.protocolId;
        }

        public boolean newerOr(final Version version) {
            return this.compareTo(version) >= 0;
        }

        public boolean newer(final Version version) {
            return this.compareTo(version) > 0;
        }

        public boolean older(final Version version) {
            return this.compareTo(version) < 0;
        }

        public String[] versions() {
            return this.versions;
        }
    }

    public enum Direction {
        SERVERBOUND,
        CLIENTBOUND;

        public State.PacketRegistry getRegistry(final State state) {
            return (this == SERVERBOUND ? state.serverBound : state.clientBound);
        }
    }

}
