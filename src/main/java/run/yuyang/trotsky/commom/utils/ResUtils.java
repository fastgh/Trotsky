package run.yuyang.trotsky.commom.utils;

import run.yuyang.trotsky.model.response.BaseResponse;

import javax.ws.rs.core.Response;

public class ResUtils {

    public static BaseResponse<Object> success = BaseResponse
            .builder()
            .result(true)
            .code(200)
            .msg("get data success")
            .build();

    public static BaseResponse<Object> failure = BaseResponse
            .builder()
            .result(false)
            .code(500)
            .msg("get data failure")
            .build();

    public static Response success() {
        return Response.ok(success).build();
    }

    public static Response failure() {
        return Response.status(500)
                .entity(failure)
                .build();
    }

    public static Response success(Object object) {
        return Response.ok(
                BaseResponse
                        .builder()
                        .result(object)
                        .code(200)
                        .msg("get data success")
                        .build()
        ).build();
    }

    public static Response failure(Object object) {
        return Response.status(500).entity(
                BaseResponse
                        .builder()
                        .result(object)
                        .code(500)
                        .msg("get data success")
                        .build()
        ).build();
    }
}
