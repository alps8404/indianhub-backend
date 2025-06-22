package com.example.adult_app;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class VideoService {
	
	private static final Logger logger = LoggerFactory.getLogger(VideoService.class);

    public List<Video> getAllVideos() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = new ClassPathResource("videos.json").getInputStream();
            byte[] bytes = is.readAllBytes();
            String json = new String(bytes);
            //System.out.println("Printing videos:\n" + json);
            logger.info("Printing image URLS");
            logger.info(json);
            return mapper.readValue(bytes, new TypeReference<List<Video>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load videos.json", e);
        }
    }
}
