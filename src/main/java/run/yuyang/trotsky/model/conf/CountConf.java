package run.yuyang.trotsky.model.conf;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class CountConf {

    private Integer noteCount;

}
