/**
 * 
 */
package com.example.rules;

import org.easyrules.core.BasicRule;

/**
 * @author Sampath
 *
 */
public class WeightCheckRule extends BasicRule
{
	private String weightCheck;
	
	public String getWeightCheck() {
		return weightCheck;
	}

	public void setWeightCheck(String wt) {
		this.weightCheck = wt;
	}
	
	
	@Override
	public boolean evaluate() 
	{
		int ww = Integer.parseInt(this.getWeightCheck());
		System.err.println("weight int value :::::: "+ww);
		if (ww <= 40)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public void execute() throws Exception 
	{
		System.err.println("Rule fired::::::");
	}

	

}
