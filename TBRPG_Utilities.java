package com.ashen.ide.TextBasedRPG;

import java.util.ArrayList;

public class TBRPG_Utilities {
	
	public static ArrayList<Object> getAttackOrder(TBRPG_Character[] ch, TBRPG_Enemy[] en) 
	{
		ArrayList<Object> order = new ArrayList <>();
		double speeds[] = new double[ch.length + en.length];
		int i = 0; for(TBRPG_Character x : ch) {speeds[i] = x.getSpeed(); i++;} for(TBRPG_Enemy x : en) {speeds[i] = x.getSpeed(); i++;}
		return order;
	}
	
}
