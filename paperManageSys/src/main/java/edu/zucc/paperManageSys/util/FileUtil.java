package edu.zucc.paperManageSys.util;

import java.io.File;
import java.io.IOException;

import edu.zucc.paperManageSys.Controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static String BASE_PATH = "D:/PaperManageSysFiles/";

    //在basePath下保存上传的文件夹
    public static boolean saveMultiFile(String basePath, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return false;
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            String filePath = basePath + "/" + file.getOriginalFilename();
            logger.debug(filePath);
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
                if(!(new File(filePath)).isFile())  return false;
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //确保目录存在，不存在则创建
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }
}