package run.yuyang.trotsky.model.conf;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class CountConf {

    private Integer noteCount;

    private Integer nextNoteId;

    private Integer dirCount;

    private Integer nextDirId;

    public void addNote() {
        nextNoteId++;
        noteCount++;
    }

    public void delNote() {
        noteCount--;
    }

    public void addDir() {
        nextDirId++;
        dirCount++;
    }

    public void delDir() {
        dirCount--;
    }

}
