package dance.brain.scbtspring.service;

import java.io.InputStream;

public interface ImageService {
    void upload(String path, InputStream image);
}
