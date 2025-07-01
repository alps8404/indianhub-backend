package com.example.adult_app;
//In your VideoService.java

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class VideoService {

 private static final String AUTH_URL = "https://api.redgifs.com/v2/auth/temporary";
 private static final String SEARCH_URL = "https://api.redgifs.com/v2/gifs/search?search=blowjob&count=20";

 public String getAuthToken() {
     RestTemplate rt = new RestTemplate();
     ResponseEntity<JsonNode> resp = rt.getForEntity(AUTH_URL, JsonNode.class);
     return resp.getBody().path("token").asText();
 }

 public List<Video> getAllVideos() {
     String token = getAuthToken();

     HttpHeaders headers = new HttpHeaders();
     headers.setBearerAuth(token);
     HttpEntity<Void> req = new HttpEntity<>(headers);

     ResponseEntity<JsonNode> resp = new RestTemplate()
         .exchange(SEARCH_URL, HttpMethod.GET, req, JsonNode.class, "desi");

     JsonNode gifs = resp.getBody().path("gifs");
     List<Video> vids = new ArrayList<>();

     for (JsonNode gif : gifs) {
         String hdUrl = gif.path("urls").path("hd").asText();

         Video v = new Video(
             gif.path("id").asText(),
             gif.path("user").path("name").asText(),
             gif.path("duration").asText(),
             "N/A",
             gif.path("urls").path("poster").asText(),
             hdUrl // add new field link
         );
         vids.add(v);	
     }
     System.out.println("Printing URLS");
     for(Video vi : vids) {
    	 System.out.println(vi.getVideoLink());
     }
     return vids;
 }
}
