package run.yuyang.trotsky.model.response;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RegisterForReflection
public class HomeInfo {

    private String nickName;

    private Integer day;

    private Integer noteCount;

}
