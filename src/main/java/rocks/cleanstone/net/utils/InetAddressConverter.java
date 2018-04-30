package rocks.cleanstone.net.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressConverter implements Converter<String, InetAddress> {

    @Nullable
    public InetAddress convert(String address) {
        try {
            return InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
