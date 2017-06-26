package com.melnychuk.objects;

import com.melnychuk.entities.Beer;

import java.util.HashSet;
import java.util.Set;

public class UploadBeersAnswer {
    private Set<Beer> newBeers = new HashSet<Beer>();
    private Set<Beer> updatedBeers = new HashSet<Beer>();
    private Set<Beer> ignoredBeers = new HashSet<Beer>();

    public void addNew(Beer beer) {
        newBeers.add(beer);
    }

    public void addUpdated(Beer beer) {
        updatedBeers.add(beer);
    }

    public void addIgnored(Beer beer) {
        ignoredBeers.add(beer);
    }

    public Set<Beer> getNewBeers() {
        return newBeers;
    }

    public Set<Beer> getUpdatedBeers() {
        return updatedBeers;
    }

    public Set<Beer> getIgnoredBeers() {
        return ignoredBeers;
    }

}
