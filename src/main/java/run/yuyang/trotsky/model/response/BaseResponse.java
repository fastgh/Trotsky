package run.yuyang.trotsky.model.response;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@RegisterForReflection
public class BaseResponse<T> {

    /**
     * 后台返回数据信息
     */
    private T result;

    /**
     * 后台返回的码值
     */
    private Integer code;

    /**
     * 后台返回信息
     */
    private String msg;

}
