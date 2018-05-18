package com.fun4g.riqin.util;

import java.util.Random;

public class RandomHelper {
	static Random x = new Random();

	public static Integer generateRandomInteger() {
		return x.nextInt();
	}
}
