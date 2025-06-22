package com.example.adult_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VideoController {
	
    @Autowired
    private VideoService videoService;

    @GetMapping("/videos")
    public List<Video>  getVideos() {
        return videoService.getAllVideos();
    }
}
