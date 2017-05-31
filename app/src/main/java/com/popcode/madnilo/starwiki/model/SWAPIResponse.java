package com.popcode.madnilo.starwiki.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SWAPIResponse implements Serializable{

    int count;
    String next;
    String previous;
    List<People> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<People> getResults() {
        return results;
    }

    public void setResults(List<People> results) {
        this.results = results;
    }
}
