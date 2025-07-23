package com.oopsfirewolf.nfts.controller;
/**
 * @Author Asadullah Nadeem
 * @descriptionThis code was developed by Asadullah Nadeem. It blocks image processing. Below * is an explanation of how the code *  works, along with some important additions
 *   1. Validate filename security
 *   2. IP blocking check
 *   3. Secure resource path construction
 *   4. Improved resource checking
 *   5. Secure content type detection
 *   6. Return with proper headers
 *   Prevent path traversal attacks. Validate path normalization. Use filename-based detection with safe defaults.Implement *   *   actual security rules here (blocked.ip.address Example Like that).
 */
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveAsset(
            @PathVariable String filename,
            HttpServletRequest request) throws IOException {
        if (!isValidFilename(filename)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (isBlocked(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String safePath = "static/assets/" + filename;
        Resource resource = new ClassPathResource(safePath);
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }
        MediaType mediaType = detectMediaType(filename);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }

    private boolean isValidFilename(String filename) {
        if (!StringUtils.hasText(filename) ||
            filename.contains("..") ||
            filename.contains("/") ||
            filename.contains("\\")) {
            return false;
        }
        try {
            Paths.get(filename);
        } catch (InvalidPathException ex) {
            return false;
        }

        return true;
    }

    private MediaType detectMediaType(String filename) {
        String contentType = null;

        if (StringUtils.hasText(filename)) {
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < filename.length() - 1) {
                String ext = filename.substring(dotIndex + 1).toLowerCase();
                contentType = switch(ext) {
                    case "png" -> "image/png";
                    case "jpg", "jpeg" -> "image/jpeg";
                    case "gif" -> "image/gif";
                    case "svg" -> "image/svg+xml";
                    case "webp" -> "image/webp";
                    case "ico" -> "image/x-icon";
                    case "css" -> "text/css";
                    case "js" -> "text/javascript";
                    case "html" -> "text/html";
                    default -> null;
                };
            }
        }

        return MediaType.parseMediaType(
            contentType != null ? contentType : MediaType.APPLICATION_OCTET_STREAM_VALUE
        );
    }

    private boolean isBlocked(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        if ("blocked.ip.address".equals(ip)) return true;
        if (userAgent != null && userAgent.toLowerCase().contains("malicious-bot")) return true;

        return false;
    }
}
