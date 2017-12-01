package com.iyunr.entity;

/**
 * @Description: TODO 复杂菜单按钮的封装，也就是包含有二级菜单项的以及菜单
 * @author Administrator  
 * @date 2017年11月7日
 */
public class Menu_complexButton extends Menu_commonButton{
	private Menu_commonButton[] sub_button;

	public Menu_commonButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Menu_commonButton[] sub_button) {
		this.sub_button = sub_button;
	}

	
}
