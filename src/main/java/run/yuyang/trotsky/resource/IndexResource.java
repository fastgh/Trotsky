package run.yuyang.trotsky.resource;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import run.yuyang.trotsky.commom.utils.ResUtils;
import run.yuyang.trotsky.service.ConfService;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author YuYang
 */
@Path("")
public class IndexResource {

    @Inject
    ConfService confService;

    @Inject
    Vertx vertx;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<String> home() {
        return vertx.fileSystem().readFile(confService.getWorkerPath() + "/index.html")
                .onItem().transform(b -> b.toString("UTF-8"));
    }

    @GET
    @Path("/css/{css}")
    @Produces("text/css")
    public Uni<String> getCss(@PathParam("css") String css) {
        return vertx.fileSystem().readFile(confService.getWorkerPath() + "/css/" + css)
                .onItem().transform(b -> b.toString("UTF-8"));
    }

    @GET
    @Path("/js/{js}")
    @Produces("application/javascript")
    public Uni<String> getJs(@PathParam("js") String js) {
        return vertx.fileSystem().readFile(confService.getWorkerPath() + "/js/" + js)
                .onItem().transform(b -> b.toString("UTF-8"));
    }

    @GET
    @Path("/img/avatar.jpg")
    @Produces("image/png")
    public Response getAvatar() {
        File file = new File(confService.getWorkerPath() + "/img/avatar.jpg");
        return Response.ok(file).build();
    }

    @GET
    @Path("/webfonts/{webfonts}")
    @Produces("font/tff")
    public Response getWebFont(@PathParam("webfonts") String webfonts) {
        File file = new File(confService.getWorkerPath() + "/webfonts/" + webfonts);
        return Response.ok(file).build();
    }

    @GET
    @Path("/{md:.*md$}")
    @Produces("text/markdown")
    public Uni<String> getMd(@PathParam("md") String md) {
        return vertx.fileSystem().readFile(confService.getWorkerPath() + "/" + md)
                .onItem().transform(b -> b.toString("UTF-8"));
    }

}
