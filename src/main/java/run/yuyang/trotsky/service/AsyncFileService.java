package run.yuyang.trotsky.service;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AsyncFileService {

    @Inject
    ConfService confService;

    @Inject
    Vertx vertx;

    public Uni<String> getFileAsync(String file) {

        return vertx.fileSystem().readFile(confService.getWorkerPath() + "/" + file)
                .onItem().transform(b -> b.toString("UTF-8"));
    }

}
