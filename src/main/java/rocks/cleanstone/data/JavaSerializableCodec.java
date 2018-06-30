package rocks.cleanstone.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

public class JavaSerializableCodec<T extends Serializable> implements Codec<T, ByteBuf> {
    @Override
    public T deserialize(ByteBuf data) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteBufInputStream(data))) {
            //noinspection unchecked
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public ByteBuf serialize(T object) throws IOException {
        ByteBuf data = Unpooled.buffer();
        ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(data);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteBufOutputStream)) {
            objectOutputStream.writeObject(object);
        }
        return data;
    }
}
