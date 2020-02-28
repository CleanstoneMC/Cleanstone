/*
 * MIT License
 *
 * Copyright (c) 2019 https://github.com/ViaVersion/ViaVersion
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
package rocks.cleanstone.endpoint.minecraft.vanilla.net.chunk;

public class HeightMapUtil {

    private HeightMapUtil() {
    }

    public static long[] encodeHeightMap(int[] heightMap) {
        final int bitsPerBlock = 9;
        long maxEntryValue = (1L << bitsPerBlock) - 1;

        int length = (int) Math.ceil(heightMap.length * bitsPerBlock / 64.0);
        long[] data = new long[length];

        for (int index = 0; index < heightMap.length; index++) {
            int value = heightMap[index];
            int bitIndex = index * 9;
            int startIndex = bitIndex / 64;
            int endIndex = ((index + 1) * bitsPerBlock - 1) / 64;
            int startBitSubIndex = bitIndex % 64;
            data[startIndex] = data[startIndex] & ~(maxEntryValue << startBitSubIndex) | ((long) value & maxEntryValue) << startBitSubIndex;
            if (startIndex != endIndex) {
                int endBitSubIndex = 64 - startBitSubIndex;
                data[endIndex] = data[endIndex] >>> endBitSubIndex << endBitSubIndex | ((long) value & maxEntryValue) >> endBitSubIndex;
            }
        }

        return data;
    }
}