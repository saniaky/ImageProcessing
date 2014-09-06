package org.imageProcessing.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author saniaky
 * @since 9/3/14
 */
public class ImageFile {
    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
