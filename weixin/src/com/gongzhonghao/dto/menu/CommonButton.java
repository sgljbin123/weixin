package com.gongzhonghao.dto.menu;

public class CommonButton extends Button {
    
    private String type;
    private String key;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "CommonButton [type=" + type + ", key=" + key + ", url=" + url
				+ "]";
	}
    
}
