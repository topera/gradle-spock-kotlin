package com.topera.hello;

/**
 * Created by topera on 23/07/17.
 */
public abstract class Calculator {

    public static void main(String[] args) {
        System.out.println("1 + 1 is " + sum(1, 1));
    }

	public static int sum(int a, int b) {
		return a + b;
	}

}
