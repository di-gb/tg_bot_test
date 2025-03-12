package org.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Photo{
    private String namePhoto;
    private List<String> way;
    URL url = new URL("изображение_viber_2025-02-19_19-14-03-997.jpg");
    public Photo(String namePhoto, List<String> way) throws MalformedURLException {
        this.namePhoto = namePhoto;
        this.way = way;
    }

    public List<String> getWay() {
        return way;
    }

    public void setWay(List<String> way) {
        this.way = way;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }


}
