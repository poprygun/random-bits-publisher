# Random objects publisher.

Can be used to simulate reactive stream of random objects of specified Type.

## Example configuration

```java

//Define and Supplier<T> to emit objects.  In this case - random beans are used.
BitIdRandomizer randomizer = new BitIdRandomizer();
enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
        .randomize(FieldDefinitionBuilder.field().named("id")
                        .ofType(String.class)
                        .inClass(Bit.class).get(), randomizer).build();

trackPublisher = new TrackPublisher<>(() -> enhancedRandom.nextObject(Bit.class)
        , () -> new Random().nextInt(5) //emmit random batches of 0 to 4 objects
, Duration.ofSeconds(1)); //every second
```

Now, read as you would a regular Flux<T>

```java
trackPublisher.getPublisher()
        .subscribe(r -> log.info("--> {}", r));

Thread.currentThread().join();
```

