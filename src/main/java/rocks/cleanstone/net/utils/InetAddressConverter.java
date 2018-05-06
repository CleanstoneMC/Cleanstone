package rocks.cleanstone.net.utils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressConverter implements Converter<String, InetAddress> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Nullable
    public InetAddress convert(@NotNull String address) {
        try {
            return InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
