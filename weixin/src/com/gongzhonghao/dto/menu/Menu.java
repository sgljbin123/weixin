package com.gongzhonghao.dto.menu;

import java.util.Arrays;

public class Menu {
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

	@Override
	public String toString() {
		return "Menu [button=" + Arrays.toString(button) + "]";
	}
    
}