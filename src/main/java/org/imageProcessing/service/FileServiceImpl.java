package org.imageProcessing.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author saniaky
 * @since 9/3/14
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = Logger.getLogger(FileServiceImpl.class);

    @Override
    public File saveFile(MultipartFile file, String path) {
        File newFile = null;
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = file.getInputStream();
            newFile = File.createTempFile("img", file.getOriginalFilename(), new File(path));
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            log.error(e);
        }
        return newFile;
    }

}
