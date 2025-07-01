package com.example.adult_app;

public class Video {
    private String title;
    private String channel;
    private String duration;
    private String views;
    private String image;
    private String videoLink; // ✅ New field

    // Constructors
    public Video() {}

    public Video(String title, String channel, String duration, String views, String image, String videoLink) {
        this.title = title;
        this.channel = channel;
        this.duration = duration;
        this.views = views;
        this.image = image;
        this.videoLink = videoLink;
    }

    // Getters & Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getViews() { return views; }
    public void setViews(String views) { this.views = views; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getVideoLink() { return videoLink; }
    public void setVideoLink(String videoLink) { this.videoLink = videoLink; }
}
