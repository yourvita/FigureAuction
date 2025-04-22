package figureauction.figureauction.web.util;

import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        File destFile = new File(uploadDir, fileName);
        try {
            // 원본 이미지
            BufferedImage originalImage = ImageIO.read(image.getInputStream());
            // 이미지 리사이징
//            BufferedImage resizedImage = Scalr.resize(originalImage, 450, 300);
            BufferedImage resizedImage = resizeWithPadding(originalImage, 450, 300);
            // 확장자 추출
            String formatName = getFileExtension(image.getOriginalFilename());

            ImageIO.write(resizedImage, formatName, destFile);
//            image.transferTo(new File(uploadDir + fileName));
        } catch (IOException e) {
            log.error("이미지 업로드 실패", e);
            return null;
        }

        return fileName;
    }

    private String getFileExtension(String fileName) {
        if(fileName == null) {
            return "jpg";
        }
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex != -1) ? fileName.substring(dotIndex + 1).toLowerCase() : "jpg";
    }

    public BufferedImage resizeWithPadding(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, width, height);

        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = canvas.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        int x = (width - scaledImage.getWidth()) / 2;
        int y = (height - scaledImage.getHeight()) / 2;
        g.drawImage(scaledImage, x, y, null);

        g.dispose();
        return canvas;
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
