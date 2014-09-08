package org.imageProcessing.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author saniaky
 * @since 9/3/14
 */
public interface FileService {

    public File saveFile(MultipartFile file, String path);

}
