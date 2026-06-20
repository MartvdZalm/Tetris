package tetris.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BagRandomizer
{
	private final Random random = new Random();
	private final List<Integer> bag = new ArrayList<>();

	public int next()
	{
		if (bag.isEmpty()) {
			refill();
		}
		return bag.remove(bag.size() - 1);
	}

	public int peek()
	{
		if (bag.isEmpty()) {
			refill();
		}
		return bag.get(bag.size() - 1);
	}

	private void refill()
	{
		bag.clear();
		for (int i = 0; i < 7; i++) {
			bag.add(i);
		}
		Collections.shuffle(bag, random);
	}
}
