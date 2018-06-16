package edu.zucc.paperManageSys.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import edu.zucc.paperManageSys.Controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static String BASE_PATH = "files/";

    public static Map<String, String> saveMultiFile(String basePath, MultipartFile[] files) {
        Map<String, String> result = new HashMap<String, String>();
        if(files == null)  {
            result.put("error","上传失败，files为null值");
            return result;
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                result.put("error", "上传失败，存在空文件");
                return result;
            }
            try {
                File temp = new File(basePath + "/" + file.getOriginalFilename());
                String path = temp.getAbsolutePath();
                makeDir(path.substring(0, path.lastIndexOf("\\")) + "/");

                File dest = new File(path);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                result.put("error", e.getMessage());
            }

        }

        return result;
    }


    //确保目录存在，不存在则创建
    private static void makeDir(String filePath) {
        logger.info("makeDir:"+filePath);
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }
}