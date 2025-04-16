package figureauction.figureauction.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Slf4j
@Component
public class FileUploadUtil {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getImagePath(MultipartFile image) throws IOException {
        if(image.isEmpty()){
            return null;
        }
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        try {
            image.transferTo(new File(uploadDir + fileName));
        } catch (IOException e) {
            log.error("이미지 업로드 실패", e);
            return null;
        }

        return fileName;
    }

    public Resource loadImage(String filename) throws  MalformedURLException {
        try {
            UrlResource resource = new UrlResource("file:" + uploadDir + filename);
            if(!resource.exists() || !resource.isReadable()) {
                throw new FileNotFoundException();
            }
            return resource;
        } catch (Exception e) {
            return new UrlResource("file:" + uploadDir +"default.png");
        }
    }

}
