/*
 * Copyright 2017-2017 ami.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of ami, Inc. ("Confidential Information")
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with ami.
 */

package com.ami.constants;

/**
 * @author: Amit Khandelwal
 * Date: 4/18/17
 */

public class SOArray {
	public static void main(String[] args) {
		int myArr[] = {1, 2, 3, 4};
		for (int i: myArr){
			i +=1;
			System.out.println(i);
		}
	}
}
