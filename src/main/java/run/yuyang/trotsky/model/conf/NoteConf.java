package run.yuyang.trotsky.model.conf;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;
import run.yuyang.trotsky.model.request.MDParam;

/**
 * @author YuYang
 */
@Data
@RegisterForReflection
public class NoteConf {

    private String name;

    private String father;

    private String path;

    private Boolean show;

    private Boolean isDir;

    public NoteConf() {
    }

    public static NoteConf map(MDParam param, String fatherDir) {
        NoteConf noteConf = new NoteConf();
        noteConf.setFather(param.getFather());
        noteConf.setShow(param.getShow());
        noteConf.setIsDir(false);
        noteConf.setPath(fatherDir + "/" + param.getName());
        noteConf.setName(param.getName());
        return noteConf;
    }

}
