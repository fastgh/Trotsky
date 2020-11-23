package run.yuyang.trotsky.service;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import run.yuyang.trotsky.model.conf.NoteConf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;

@ApplicationScoped
public class FileService {

    @Inject
    ConfService confService;

    @Inject
    Vertx vertx;

    public String getRelPath(String path) {
        if (null == path || "".equals(path) || ".".equals(path)) {
            return new File("").getAbsolutePath();
        } else {
            File file = new File(path);

            if (file.exists() && file.isDirectory()) {
                return file.getAbsolutePath();
            }
            if (file.mkdir()) {
                return file.getAbsolutePath();
            } else {
                return null;
            }
        }
    }

    public boolean copyStaticFile(String path) {
        String[] files = new String[]{
                "index.html", "README.md", "_coverpage.md", "notes.md",
                ".trotsky/user.json", ".trotsky/index.json", ".trotsky/note.json", ".trotsky/count.json", ".trotsky/dir.json",
                "img/avatar.jpg",
                "js/docsify.min.js",
                "css/vue.css", "css/index.css",
                "webfonts/JetBrainsMono-Regular.ttf",
                "notes/被背叛的革命.md",
                "notes/被背叛的革命/导言-本书的目的.md",
                "notes/被背叛的革命/第一章-已经成就了什么.md",
                "notes/论苏俄革命与国际形势的四篇报导/与美国联合通信社的代表谈话.md",
                "notes/论苏俄革命与国际形势的四篇报导/答纽约泰晤士报编辑部的问题.md"
        };
        String[] dirs = new String[]{
                "img", ".trotsky", "notes", "js", "css", "webfonts", "notes", "notes/被背叛的革命", "notes/论苏俄革命与国际形势的四篇报导"
        };
        for (String dir : dirs) {
            File file = new File(path + "/" + dir);
            if (!(file.exists() && file.isDirectory())) {
                if (!file.mkdir()) {
                    System.out.println("失败❌ " + file.getAbsolutePath());
                    return false;
                }
                System.out.println("创建✅ " + file.getAbsolutePath());
            }
        }
        for (String fileName : files) {
            File file = new File(path + "/" + fileName);
            if (!file.exists()) {
                vertx.fileSystem()
                        .copyBlocking("static/" + fileName, path + "/" + fileName);
            } else {
                System.out.println("已存在✅ " + file.getAbsolutePath());
            }

        }
        return true;
    }

    public void saveNewFile(String path, String text, NoteConf noteConf) {
        vertx.fileSystem().createFile(path, res -> {
            if (res.succeeded()) {
                vertx.fileSystem().writeFile(path, Buffer.buffer(text), respose -> {
                    if (respose.succeeded()) {
                        confService.addNoteConfAndSave(noteConf);
                    }
                });
            } else {
                System.out.println(res.cause());
            }
        });
    }

    public void delFile(String path, String name) {
        vertx.fileSystem().delete(confService.getWorkerPath() + "/" + path, res -> {
            if (res.succeeded()) {
                confService.delNoteConfAndSave(name);
            }
        });
    }

    public boolean existFile(String path) {
        return vertx.fileSystem().existsBlocking(confService.getWorkerPath() + path);
    }

    public String getFileSync(String file) {
        return vertx.fileSystem().readFileBlocking(confService.getWorkerPath() + "/" + file).toString();
    }

}
