package rocks.cleanstone.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class JavaSerializableCodec<T extends Serializable> implements Codec<T, ByteBuf> {
    @Override
    public T deserialize(ByteBuf data) throws IOException {
        byte[] bytes = new byte[data.readableBytes()];
        data.readBytes(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            //noinspection unchecked
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public ByteBuf serialize(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
        }
        return Unpooled.wrappedBuffer(byteArrayOutputStream.toByteArray());
    }
}
