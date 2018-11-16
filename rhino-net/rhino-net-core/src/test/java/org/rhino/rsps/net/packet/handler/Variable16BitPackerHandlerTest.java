package org.rhino.rsps.net.packet.handler;

import org.junit.Assert;
import org.junit.Test;
import org.rhino.rsps.net.packet.Packet;
import org.rhino.rsps.net.packet.definition.DefaultPacketDefinition;
import org.rhino.rsps.net.packet.definition.PacketDefinition;
import org.rhino.rsps.net.stream.ByteBufferInputStream;
import org.rhino.rsps.net.stream.InputStream;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

public class Variable16BitPackerHandlerTest {

    private static final int TEST_OPCODE = 69;
    private static final int TEST_LENGTH = 3;
    private static final PacketDefinition TEST_DEFINITION = new DefaultPacketDefinition(TEST_OPCODE, TEST_LENGTH, null);

    private static final PacketHandler TEST_READER = new Variable16BitPacketHandler();

    private static final byte[] TEST_DATA = {
            0, TEST_LENGTH, 2, 3, 4
    };

    @Test
    public void testNormalRead() throws IOException {
        Packet packet = TEST_READER.read(TEST_DEFINITION, createPacketData(TEST_DATA));

        Assert.assertEquals(TEST_OPCODE, packet.getOpcode());
        Assert.assertEquals(TEST_LENGTH, packet.getPayload().available());
        Assert.assertEquals(TEST_DEFINITION, packet.getDefinition());

        Assert.assertEquals(TEST_DATA[2], packet.getPayload().readByte());
        Assert.assertEquals(TEST_DATA[3], packet.getPayload().readByte());
        Assert.assertEquals(TEST_DATA[4], packet.getPayload().readByte());
    }

    @Test(expected = BufferUnderflowException.class)
    public void testReadWithLengthTooHigh() throws IOException {
        PacketDefinition definition = new DefaultPacketDefinition(TEST_OPCODE, PacketDefinition.HeaderType.VARIABLE_SIZE_16, null);
        TEST_READER.read(definition, createPacketData((byte) 0, (byte) 10, (byte) 0));
    }

    private InputStream createPacketData(byte... data) {
        return new ByteBufferInputStream((ByteBuffer) ByteBuffer.allocate(1 + data.length)
                .put((byte) TEST_OPCODE)
                .put(data)
                .flip());
    }
}
