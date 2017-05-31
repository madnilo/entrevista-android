package com.popcode.madnilo.starwiki.model;

import java.io.Serializable;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 */

public class SWFAPIResponse implements Serializable {
    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
