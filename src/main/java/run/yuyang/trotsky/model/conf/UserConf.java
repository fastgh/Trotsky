package run.yuyang.trotsky.model.conf;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class UserConf {

    private String version;

    private String email;

    private String password;

    private String nickName;

    private String buildTime;

}
