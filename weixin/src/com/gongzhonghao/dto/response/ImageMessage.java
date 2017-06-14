package com.gongzhonghao.dto.response;

public class ImageMessage extends BaseMessage {
    
    private String MediaId;

    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}