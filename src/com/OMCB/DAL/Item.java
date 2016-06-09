/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.OMCB.DAL;

/**
 *
 * @author atchowdhury
 */
class Item {
private String firstText = null;

	public void setFirstText(String str){
		firstText =  str;
	}

	public String getFirstText(){
		if(firstText == null){
			return null;
		}else{
			return firstText;
		}
	}
}
