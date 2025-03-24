package identity.TuanHuy.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConversionService {
    @Value("${app.upload.dir:${user.home}/youtube-mp3}")
    private String uploadDir;

    @Value("${api.base-url}")
    private String downloadBaseUrl;

    @PostConstruct
    public void init() {
        System.out.println("Upload Directory: " + uploadDir);
        System.out.println("Upload Directory: " + downloadBaseUrl);
    }

    public String convertYoutubeToMp3(String youtubeLink, Integer quality) throws Exception {
        // Validate YouTube link
        if (!isValidYoutubeUrl(youtubeLink)) {
            throw new IllegalArgumentException("Invalid YouTube URL");
        }

        // Validate quality
        if (quality == null) {
            quality = 192; // Default quality
        }

        // Only allow specific quality values
        if (quality != 64 && quality != 128 && quality != 192 && quality != 256 && quality != 320) {
            throw new IllegalArgumentException("Invalid quality. Allowed values: 64, 128, 192, 256, 320");
        }

        // Extract video ID for file naming
        String videoId = extractVideoId(youtubeLink);
        String fileName = videoId + "-" + quality + "kbps-" + UUID.randomUUID().toString().substring(0, 8) + ".mp3";

        // Create upload directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path outputPath = Paths.get(uploadDir, fileName);

        // Prepare youtube-dl command with audio quality option
        List<String> command = new ArrayList<>();
        command.add("E:\\yt-dlp\\yt-dlp.exe"); // Đường dẫn đầy đủ đến yt-dlp
        command.add("--extract-audio");
        command.add("--audio-format");
        command.add("mp3");
        command.add("--audio-quality");
        command.add(quality.toString() + "k");
        command.add("--output");
        command.add(outputPath.toString().replace(".mp3", ".%(ext)s"));
        // Chỉ định đường dẫn chính xác của FFmpeg
        command.add("--ffmpeg-location");
        command.add("E:\\ffmpeg\\ffmpeg-7.1.1-essentials_build\\bin\\ffmpeg.exe"); // Đường dẫn đầy đủ
        command.add(youtubeLink);

        // Execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        // Read the output of the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            // Get error message
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            throw new RuntimeException("Error converting YouTube video to MP3: " + errorOutput.toString());
        }

        // Verify the file exists
        File mp3File = new File(outputPath.toString());
        if (!mp3File.exists()) {
            throw new RuntimeException("Conversion completed but file not found. Check logs for details: " + output.toString());
        }

        // Return the download URL
        return downloadBaseUrl + "/" + fileName;
    }

    private boolean isValidYoutubeUrl(String url) {
        // Simple validation for YouTube URLs
        return url != null &&
                (url.startsWith("https://www.youtube.com/") ||
                        url.startsWith("https://youtube.com/") ||
                        url.startsWith("https://youtu.be/"));
    }

    private String extractVideoId(String youtubeUrl) {
        String videoId = "";
        if (youtubeUrl.contains("youtu.be/")) {
            videoId = youtubeUrl.substring(youtubeUrl.lastIndexOf("/") + 1);
            // Remove any parameters
            if (videoId.contains("?")) {
                videoId = videoId.substring(0, videoId.indexOf("?"));
            }
        } else if (youtubeUrl.contains("watch?v=")) {
            videoId = youtubeUrl.split("v=")[1];
            // Remove any additional parameters
            if (videoId.contains("&")) {
                videoId = videoId.substring(0, videoId.indexOf("&"));
            }
        }
        return videoId;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found: " + fileName, ex);
        }
    }
}
