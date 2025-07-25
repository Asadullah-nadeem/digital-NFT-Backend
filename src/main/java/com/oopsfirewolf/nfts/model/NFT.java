package com.oopsfirewolf.nfts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NFT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String openseaUrl;
    private String imageUrl;
    private int clicks;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOpenseaUrl() { return openseaUrl; }
    public void setOpenseaUrl(String openseaUrl) { this.openseaUrl = openseaUrl; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getClicks() { return clicks; }
    public void setClicks(int clicks) { this.clicks = clicks; }
}



