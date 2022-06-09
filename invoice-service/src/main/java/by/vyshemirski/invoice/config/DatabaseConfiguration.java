package by.vyshemirski.invoice.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    private final ConnectionFactory connectionFactory;

    @Override
    public ConnectionFactory connectionFactory() {
        return connectionFactory;
    }

    @Override
    protected List<Object> getCustomConverters() {
        return List.of(new UUIDToByteArrayConverter(), new ByteArrayToUUIDConverter());
    }

    @WritingConverter
    private class UUIDToByteArrayConverter implements Converter<UUID, byte[]> {
        @Override
        public byte[] convert(UUID source) {
            ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
            bb.putLong(source.getMostSignificantBits());
            bb.putLong(source.getLeastSignificantBits());
            return bb.array();
        }
    }

    @ReadingConverter
    private class ByteArrayToUUIDConverter implements Converter<byte[], UUID> {
        @Override
        public UUID convert(byte[] source) {
            return UUID.nameUUIDFromBytes(source);
        }
    }
}