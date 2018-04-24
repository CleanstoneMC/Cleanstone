package rocks.cleanstone.net.minecraft.packet.enums;

public enum SteerVehicleFlag {
    JUMP(0x1),
    UNMOUNT(0x2);

    private final int bit;

    SteerVehicleFlag(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public static SteerVehicleFlag fromBitMask(int bitmask) {
        for (SteerVehicleFlag steerVehicleFlag : SteerVehicleFlag.values()) {
            if ((bitmask & (1 << steerVehicleFlag.getBit())) == 1) {
                return steerVehicleFlag;
            }
        }

        return null;
    }
}
