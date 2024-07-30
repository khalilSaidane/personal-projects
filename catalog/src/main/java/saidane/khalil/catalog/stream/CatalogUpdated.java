package saidane.khalil.catalog.stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogUpdated {

    private final StreamBridge streamBridge;

    public <T> void invalidateCache(T event) {
        log.info(format("write/update action has been made on object : %s", event.toString()));
        var message = MessageBuilder.withPayload(event)
                .setHeader("routingKey", "queuing.catalog.updated")
                .build();
        streamBridge.send("invalidate-cache-out-0", message);
    }

}
