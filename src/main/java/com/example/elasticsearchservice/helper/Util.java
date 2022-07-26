package com.example.elasticsearchservice.helper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.util.logging.Logger;

@Slf4j
public class Util {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(Util.class);

    public static String loadAsString(final String path) {
        try {
            final File resource = new ClassPathResource(path).getFile();

            return new String(Files.readAllBytes(resource.toPath()));
        } catch (final Exception e) {
            log.info("ERROR " + e.getMessage());
            return null;
        }
    }
}
