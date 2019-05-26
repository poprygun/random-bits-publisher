package io.microsamples.streams;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Random;


public class BitPublisherTest {
    private static EnhancedRandom enhancedRandom;

    private BitPublisher<Bit> bitPublisher;

    @Before
    public void initPublisher() {

        BitIdRandomizer randomizer = new BitIdRandomizer();
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(FieldDefinitionBuilder.field().named("id")
                                .ofType(String.class)
                                .inClass(Bit.class).get(), randomizer)
                .build();

        bitPublisher = new BitPublisher<>(() -> enhancedRandom.nextObject(Bit.class)
                , () -> new Random().nextInt(5) //emmit batches 0 - 4
        , Duration.ofNanos(1000000)); //every millisecond
    }


    @Test
    @Ignore
    /**
     * This will continuosly run if not ignored - here just for illustration.
     */

    public void shouldKeepEmitting() throws InterruptedException {

        bitPublisher.getPublisher()
                .subscribe(System.out::println);

        Thread.currentThread().join();
    }

    @Test
    public void shouldEmitSpecifiedNumberOfTracks() {
        int expectSome = 13;

        StepVerifier.create(bitPublisher.getPublisher()
//                .log() //print the output
        .limitRequest(expectSome))
                .expectSubscription()
                .expectNextCount(expectSome)
                .verifyComplete();
    }

}
