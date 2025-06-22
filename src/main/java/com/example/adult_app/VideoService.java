package com.example.adult_app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Value("${pexels.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.pexels.com/videos/search?query=adult&per_page=10";

    public List<Video> getAllVideos() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode videosNode = root.path("videos");

            List<Video> videoList = new ArrayList<>();

            for (JsonNode videoNode : videosNode) {
                String title = videoNode.path("url").asText(); // Optional: you could parse more meaningful title if available
                String channel = videoNode.path("user").path("name").asText();
                String duration = videoNode.path("duration").asText() + " seconds";
                String image = videoNode.path("image").asText();

                // Pick the first HD video file
                String videoLink = "";
                for (JsonNode file : videoNode.path("video_files")) {
                    if ("hd".equals(file.path("quality").asText())) {
                        videoLink = file.path("link").asText();
                        break;
                    }
                }

                Video video = new Video(title, channel, duration, "N/A", image);
                videoList.add(video);
            }
            System.out.println("Printing videos :");
            for(Video vi : videoList) {
            	System.out.println(" :"+vi.getChannel());
            }
            return videoList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch videos from Pexels", e);
        }
    }
}
