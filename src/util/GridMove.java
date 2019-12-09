package util;

import java.util.Objects;

public class GridMove {
	private int x, y;

	public GridMove(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	@Override
	public String toString() {
		//TODO generalize for bigger than 676 in x
		if (x >= 26) return Character.toString(x/26 + 'a' - 1) + Character.toString(x%26 + 'a') + (y+1);
		else return Character.toString(x + 'a') + (y+1);
	}
	
	public static GridMove parse(String str) {
		var chars = str.toCharArray();
		int x = 0;
		int i;
		for (i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c >= 'a' && c <= 'z') x = 26*x + (c - 'a' + 1);
			else if (c >= 'A' && c <= 'Z') x = 26*x + (c - 'A' + 1);
			else break;
		}
		if (x <= 0) throw new IllegalArgumentException("Invalid first part of move");
		String sub = str.substring(i);
		return new GridMove(x-1, Integer.parseInt(sub)-1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		GridMove other = (GridMove) obj;
		return x == other.x && y == other.y;
	}
}
