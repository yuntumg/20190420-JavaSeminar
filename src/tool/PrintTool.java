package tool;

public class PrintTool {

	public static void printTitle(String title) {
		System.out.println();
		System.out.println(title + ":");	
	}
	
	public static void printContent(Object content) {
		System.out.println("    " + content);
	}
}
