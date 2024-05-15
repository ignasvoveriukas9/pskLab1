package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@ApplicationScoped
public class AsyncService {

        private final ExecutorService executor = Executors.newFixedThreadPool(10);

        public CompletableFuture<Integer> GetIntAsync() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000); // Simulate intensive work
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return 100;
            }, executor);
        }
}
