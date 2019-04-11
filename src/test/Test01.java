package test;

public class Test01 {

	public static void main(String[] args) {

		Integer a = new Integer(127);
		Integer b = new Integer(127);
		System.out.println(" a == b      の結果は：" + (a == b));
		System.out.println(" a.equals(b) の結果は：" + a.equals(b));
	}
}
