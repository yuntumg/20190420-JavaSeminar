package test;

public class Test04 {

	public static void main(String[] args) {
		
		Boolean a = Boolean.valueOf(true);
		Boolean b = Boolean.valueOf("true");

		System.out.println(" a == b      の結果は：" + (a == b));
		System.out.println(" a.equals(b) の結果は：" + a.equals(b));
		System.out.println(" a == true   の結果は：" + (a == true));
	}
}
