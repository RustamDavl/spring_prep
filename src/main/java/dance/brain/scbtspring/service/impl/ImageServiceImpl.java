package dance.brain.scbtspring.service.impl;

import dance.brain.scbtspring.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
public class ImageServiceImpl implements ImageService {


    private final String BUCKET;

    @Autowired
    public ImageServiceImpl(@Value("${app.images.bucket}") String bucket) {
        BUCKET = bucket;
    }

    @Override
    public void upload(String path, InputStream image) {
        Path fullPath = Path.of(path, BUCKET);
        try (image) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, image.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
