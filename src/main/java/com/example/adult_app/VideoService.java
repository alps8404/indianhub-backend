package com.example.adult_app;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class VideoService {
    @Value("${PEXELS_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PEXELS_API_URL =
        "https://api.pexels.com/videos/search?query=adult&per_page=40";

    public List<Video> getAllVideos() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<PexelsResponse> response = restTemplate.exchange(
            PEXELS_API_URL, HttpMethod.GET, request, PexelsResponse.class);

        return response.getBody().videos().stream()
            .map(v -> new Video(
                v.user().name(),                 // channel = user name
                v.url(),                          // title
                formatDuration(v.duration()),     // duration
                String.valueOf(v.video_files().get(0).width) + " x " + 
                   String.valueOf(v.video_files().get(0).height),
                v.image()))
            .toList();
    }

    private String formatDuration(int totalSec) {
        int min = totalSec / 60, sec = totalSec % 60;
        return String.format("%02d:%02d", min, sec);
    }

    // DTOs for Pexels response
    record PexelsResponse(List<PexelsVideo> videos) {}
    record PexelsVideo(int duration, String url, String image, PexelsUser user, List<VideoFile> video_files){}
    record PexelsUser(String name) {}
    record VideoFile(int width, int height, String link) {}
}

