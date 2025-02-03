package com.myproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class SocialMediaModel {

    @ValueMapValue
    private String youtubeUrl;
    
    @ValueMapValue
    private String facebookUrl;
    
    @ValueMapValue
    private String twitterUrl;

    public String getYouTubeUrl() {
        return youtubeUrl;
    }

    public void setYouTubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }
}
