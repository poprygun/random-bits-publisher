package io.microsamples.streams;

import lombok.Data;

import java.util.UUID;

@Data
public class Bit {
    private UUID id;
    private long latitude;
    private long longitude;
}
