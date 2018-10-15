package rocks.cleanstone.data;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class JavaSerializableCodec<T extends Serializable> implements InOutCodec<T, ByteBuf> {
    @Override
    @SuppressWarnings("unchecked")
    public T decode(ByteBuf data) throws IOException {
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteBufInputStream(data))) {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public ByteBuf encode(T object) throws IOException {
        final ByteBuf data = Unpooled.buffer();
        final ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(data);
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteBufOutputStream)) {
            objectOutputStream.writeObject(object);
        }
        return data;
    }
}
