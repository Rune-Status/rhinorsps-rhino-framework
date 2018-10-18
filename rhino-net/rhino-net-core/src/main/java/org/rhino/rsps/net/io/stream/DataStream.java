package org.rhino.rsps.net.io.stream;

import java.io.IOException;

interface DataStream extends AutoCloseable {

    /**
     * The default endianness should none be given
     */
    Endianness DEFAULT_ENDIANNESS = Endianness.BIG_ENDIAN;

    /**
     * amount of data available
     *
     * @return
     * @throws IOException
     */
    int available() throws IOException;

    /**
     * Closes the stream
     *
     * @throws IOException
     */
    void close() throws IOException;

    /**
     * indicates whether or not the stream is closed
     *
     * @return
     * @throws IOException
     */
    boolean isClosed() throws IOException;

    /**
     * Indicates the order of the bytes in a packet
     */
    enum Endianness {

        /**
         * The most significant byte (MSB) value, 0Ah, is at the lowest address.
         * The other bytes follow in decreasing order of significance.
         * This is akin to left-to-right reading in hexadecimal order.
         */
        BIG_ENDIAN,

        /**
         * The least significant byte (LSB) value, 0Dh, is at the lowest address.
         * The other bytes follow in increasing order of significance. This is akin to right-to-left
         * reading in hexadecimal order.
         */
        LITTLE_ENDIAN,

        /**
         * Numerous other orderings, generically called middle-endian or mixed-endian, are possible.
         */
        MIDDLE_ENDIAN;

    }
}
