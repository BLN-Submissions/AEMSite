package com.myproject.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class SocialMediaModelTest {

    private SocialMediaModel socialMediaModel;

    @BeforeEach
    public void setUp() {
        socialMediaModel = new SocialMediaModel();
    }

    @Test
    void testGetYouTubeUrl() {
        String youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        socialMediaModel.setYouTubeUrl(youtubeUrl);
        
        assertEquals(youtubeUrl, socialMediaModel.getYouTubeUrl());
    }

    @Test
    void testGetFacebookUrl() {
        String facebookUrl = "https://www.facebook.com/facebook";
        socialMediaModel.setFacebookUrl(facebookUrl);
        
        assertEquals(facebookUrl, socialMediaModel.getFacebookUrl());
    }

    @Test
    void testGetTwitterUrl() {
        String twitterUrl = "https://twitter.com/Twitter";
        socialMediaModel.setTwitterUrl(twitterUrl);
        
        assertEquals(twitterUrl, socialMediaModel.getTwitterUrl());
    }
}
