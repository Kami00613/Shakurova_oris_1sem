package org.example.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", "***");
            config.put("api_key", "***");
            config.put("api_secret", "***");
            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }

}