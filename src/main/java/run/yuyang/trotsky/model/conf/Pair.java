package run.yuyang.trotsky.model.conf;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

/**
 * @author YuYang
 */
@Data
@RegisterForReflection
public class Pair {

    private String key;

    private String value;

}
