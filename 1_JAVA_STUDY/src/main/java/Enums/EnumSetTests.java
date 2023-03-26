package Enums;

import java.util.EnumSet;

enum Size {
    XXS,
    XS,
    S,
    M,
    L,
    XL,
    XXL,
    XXXL;
}

public class EnumSetTests {
	
	public static void Range() {
		EnumSet<Size> excludeSmallSize = EnumSet.range(Size.M, Size.XXXL);
		System.out.println("[Size.M - Size.XXXL]: " + excludeSmallSize);
		
		System.out.println("[Size.XXS - Size.L]: " + EnumSet.range(Size.XXS, Size.L));
	}
	
	public static void Contains() {
		EnumSet<Size> excludeSmallSize = EnumSet.range(Size.M, Size.XXXL);
		System.out.println("[Size.M - Size.XXXL]: " + excludeSmallSize);
		System.out.println(excludeSmallSize.contains(Size.XXXL));
		System.out.println(excludeSmallSize.contains(Size.XXS));
	}
	
	public static void main(String[] args) {
		// Range();
		Contains();
	}
}
