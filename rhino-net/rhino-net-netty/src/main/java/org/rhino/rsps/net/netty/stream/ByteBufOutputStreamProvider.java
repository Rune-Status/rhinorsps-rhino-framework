package org.rhino.rsps.net.netty.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.rhino.rsps.net.stream.provider.OutputStreamProvider;

/**
 * reservation for future implementations that use pooled buffers if necessary
 */
public class ByteBufOutputStreamProvider implements OutputStreamProvider<ByteBuf> {

    @Override
    public ByteBufOutputStream provide(ByteBuf byteBuf) {
        return new ByteBufOutputStream(byteBuf);
    }

    @Override
    public ByteBufOutputStream create(int capacity) {
        return this.provide(Unpooled.buffer(capacity));
    }

}