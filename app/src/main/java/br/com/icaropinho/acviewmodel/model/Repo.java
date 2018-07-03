package br.com.icaropinho.acviewmodel.model;

import com.squareup.moshi.Json;

/**
 * Created by icaro on 03/07/2018.
 */

public class Repo {

    private long id;

    private String name;

    private String description;

    private User owner;

    @Json(name = "stargazers_count")
    private long stars;

    @Json(name = "forks_count")
    private long forks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public long getForks() {
        return forks;
    }

    public void setForks(long forks) {
        this.forks = forks;
    }
}