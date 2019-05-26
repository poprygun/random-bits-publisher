package io.microsamples.streams;

import lombok.Data;

import java.util.UUID;

@Data
public class Bit {
    private UUID id;
    private double latitude;
    private double longitude;
}
