package com.example.ytmp3;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

// Represents a class which converts YouTube video into an mp3 audio file
@Service
public class VideoProcessor {

    // Requires : Song/video should not be 10 minutes or longer (should be reasonably long)
    // Takes a YouTube url string and executes the yt-dlp command to extract the audio from the video which has
    // that url. The HttpServletResponse object is used to write the output of the yt-dlp command to the http response.
    public static int videoToMp3(String url, HttpServletResponse response) {


        try {
            ProcessBuilder ytProcessBuilder = new ProcessBuilder(
                    "yt-dlp",      // Command: yt-dlp
                    "-x",                    // Option: Extraction mode
                    "--audio-format", "mp3", // Audio format to mp3
                    "-o",                    // Output file option
                    "-",                     // Output to sdout
                    url                      // Video URL
            );


            // Starts the yt dlp process
            Process ytProcess = ytProcessBuilder.start();

            response.setContentType("audio/mpeg");

            InputStream in = ytProcess.getInputStream();

            // Create a buffer to store the output of the yt-dlp command and write it to the http response sent by
            // the server.
            int bufferSize = 100 * 1024 * 1024;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            int exitCode = ytProcess.waitFor();

            if (exitCode == 0) {
                System.out.println("Video downloaded and converted successfully");
            } else {
                System.err.println("Conversion failed exit code: " + exitCode);
            }
            return exitCode;

        } catch (IOException e) {
            System.err.println("Error during conversion: " + e.getMessage());
            return -1;
        } catch (InterruptedException e) {
            System.err.println("Error during conversion: " + e.getMessage());
            return -2;
        }
    }






}
