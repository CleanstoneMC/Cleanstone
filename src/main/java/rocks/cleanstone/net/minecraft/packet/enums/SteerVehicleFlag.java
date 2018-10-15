package rocks.cleanstone.net.minecraft.packet.enums;

public enum SteerVehicleFlag {
    JUMP(0x1),
    UNMOUNT(0x2);

    private final int bit;

    SteerVehicleFlag(int bit) {
        this.bit = bit;
    }

    public static SteerVehicleFlag fromBitMask(int bitmask) {
        for (final SteerVehicleFlag steerVehicleFlag : SteerVehicleFlag.values()) {
            final int bit = steerVehicleFlag.getBit();
            if ((bitmask & bit) == bit) {
                return steerVehicleFlag;
            }
        }

        return null;
    }

    public int getBit() {
        return bit;
    }
}
