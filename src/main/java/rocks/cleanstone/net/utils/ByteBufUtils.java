/*
 * This file is part of Flow Network, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 Flow Powered <https://flowpowered.com/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package rocks.cleanstone.net.utils;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.utils.Vector;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * A class containing various utility methods that act on byte buffers.
 */
public class ByteBufUtils {

    /**
     * Reads an UTF8 string from a byte buffer.
     *
     * @param buf The byte buffer to read from
     * @return The read string
     * @throws java.io.IOException If the reading fails
     */
    public static String readUTF8(ByteBuf buf, int maxLength) throws IOException {
        // Read the string's length
        final int len = readVarInt(buf);
        if (len > maxLength) // Cleanstone
            throw new IOException("String length " + len + " exceeds maxLength " + maxLength);
        final byte[] bytes = new byte[len];
        buf.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String readUTF8(ByteBuf buf) throws IOException { // Cleanstone
        return readUTF8(buf, Short.MAX_VALUE);
    }

    /**
     * Writes an UTF8 string to a byte buffer.
     *
     * @param buf   The byte buffer to write too
     * @param value The string to write
     * @throws java.io.IOException If the writing fails
     */
    public static void writeUTF8(ByteBuf buf, String value) throws IOException {
        final byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        if (bytes.length >= Short.MAX_VALUE) {
            throw new IOException("Attempt to write a string with a length greater than Short.MAX_VALUE to ByteBuf!");
        }
        // Write the string's length
        writeVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }

    /**
     * Reads an integer written into the byte buffer as one of various bit sizes.
     *
     * @param buf The byte buffer to read from
     * @return The read integer
     * @throws java.io.IOException If the reading fails
     */
    public static int readVarInt(ByteBuf buf) throws IOException {
        int readableBytes = buf.readableBytes(); // Cleanstone
        int out = 0;
        int bytes = 0;
        byte in;
        while (true) {
            // Cleanstone: Throw custom exception if there aren't enough readable bytes
            if (--readableBytes < 0) throw new NotEnoughReadableBytesException("Unable to read varint");
            in = buf.readByte();
            out |= (in & 0x7F) << (bytes++ * 7);
            if (bytes > 5) {
                throw new IOException("Attempt to read int bigger than allowed for a varint!");
            }
            if ((in & 0x80) != 0x80) {
                break;
            }
        }
        return out;
    }

    /**
     * Writes an integer into the byte buffer using the least possible amount of bits.
     *
     * @param buf   The byte buffer to write too
     * @param value The integer value to write
     */
    public static void writeVarInt(ByteBuf buf, int value) {
        byte part;
        while (true) {
            part = (byte) (value & 0x7F);
            value >>>= 7;
            if (value != 0) {
                part |= 0x80;
            }
            buf.writeByte(part);
            if (value == 0) {
                break;
            }
        }
    }

    public static int getVarIntSize(int value) { // Cleanstone
        int size = 0;
        while (true) {
            value >>>= 7;
            size++;
            if (value == 0) {
                break;
            }
        }
        return size;
    }

    /**
     * Reads an integer written into the byte buffer as one of various bit sizes.
     *
     * @param buf The byte buffer to read from
     * @return The read integer
     * @throws java.io.IOException If the reading fails
     */
    public static long readVarLong(ByteBuf buf) throws IOException {
        long out = 0;
        int bytes = 0;
        byte in;
        while (true) {
            in = buf.readByte();
            out |= (in & 0x7F) << (bytes++ * 7);
            if (bytes > 10) {
                throw new IOException("Attempt to read long bigger than allowed for a varlong!");
            }
            if ((in & 0x80) != 0x80) {
                break;
            }
        }
        return out;
    }

    /**
     * Writes an integer into the byte buffer using the least possible amount of bits.
     *
     * @param buf   The byte buffer to write too
     * @param value The long value to write
     */
    public static void writeVarLong(ByteBuf buf, long value) {
        byte part;
        while (true) {
            part = (byte) (value & 0x7F);
            value >>>= 7;
            if (value != 0) {
                part |= 0x80;
            }
            buf.writeByte(part);
            if (value == 0) {
                break;
            }
        }
    }

    public static void writeVector(ByteBuf byteBuf, Vector vector) {
        final long x = vector.getXAsLong();
        final long y = vector.getYAsLong();
        final long z = vector.getZAsLong();

        byteBuf.writeLong((x & 0x3ffffff) << 38 | (y & 0xfff) << 26 | z & 0x3ffffff);
    }

    public static Vector readVector(ByteBuf byteBuf) {
        long val = byteBuf.readLong();

        long x = val >> 38;
        long y = (val >> 26) & 0xFFF;
        long z = val << 38 >> 38;

        return new Vector(x, y, z);
    }

    public static void writeUUID(ByteBuf byteBuf, UUID uuid) {
        byteBuf.writeLong(uuid.getMostSignificantBits());
        byteBuf.writeLong(uuid.getLeastSignificantBits());
    }


    public static UUID readUUID(ByteBuf byteBuf) {
        return new UUID(byteBuf.readLong(), byteBuf.readLong());
    }

    public static void writeItemStack(ByteBuf byteBuf, ItemStack item) {
        byteBuf.writeShort(item.getType().getID());
        byteBuf.writeByte(item.getAmount());
        byteBuf.writeShort(item.getMetadata());
        byteBuf.writeByte(0); // TODO Item NBT
    }

    @Nullable
    public static ItemStack readItemStack(ByteBuf byteBuf, MaterialRegistry materialRegistry) {
        short itemID = byteBuf.readShort();
//        ItemType itemType = materialRegistry.getItemType(itemID); //TODO: Add ItemStack Read
//        if (itemID != -1) {
//            Preconditions.checkNotNull(itemType, "Cannot find itemType with ID " + itemID);
//            byte itemCount = byteBuf.readByte();
//            short itemMetadata = byteBuf.readShort();
//            byte nbtStartByte = byteBuf.readByte(); // TODO Item NBT
//            return new SimpleItemStack(itemType, itemCount, itemMetadata, null);
//        }
        return null;
    }
}