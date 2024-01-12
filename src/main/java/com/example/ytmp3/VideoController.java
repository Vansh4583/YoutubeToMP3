package com.example.ytmp3;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

// Represents a class that handles HTTP requests related to video conversion
@RestController
@RequestMapping("/api")
public class VideoController {


    @PostMapping("/convert")
    public void convertVideoToMp3(@RequestParam String url, HttpServletResponse response) {
        // Call the videoToMp3 method with the youtubeUrl and response
        VideoProcessor.videoToMp3(url, response);
    }
}
