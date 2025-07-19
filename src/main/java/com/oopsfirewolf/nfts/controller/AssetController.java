package com.oopsfirewolf.nfts.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLConnection;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveAsset(
            @PathVariable String filename,
            HttpServletRequest request) throws IOException {

        String clientIp = request.getRemoteAddr();

        // Optional: Block certain IPs / headers
        if (isBlocked(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Resource resource = new ClassPathResource("static/assets/" + filename);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // FIXED: Use Java method to detect correct image mime type
        String contentType = URLConnection.guessContentTypeFromName(resource.getFilename());
        if (contentType == null) {
            contentType = "image/png"; // fallback
        }

        // Return content with proper headers
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    private boolean isBlocked(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        // Example blocking logic (customize as needed)
        if ("blocked.ip.address".equals(ip)) return true;
        if (userAgent != null && userAgent.toLowerCase().contains("some-bad-bot")) return true;

        return false;
    }
}
