package rocks.cleanstone.net.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class InetAddressConverter implements Converter<String, InetAddress> {
    @Nullable
    @Bean
    public InetAddress convert(String address) {
        try {
            return InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
