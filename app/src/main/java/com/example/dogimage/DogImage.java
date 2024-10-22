package com.example.dogimage;


import com.google.gson.annotations.SerializedName;

public class DogImage {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public DogImage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DogImage{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
