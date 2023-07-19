import java.util.Scanner;

public class Test {
	interface FileTextEvent {
		void onChange(int i);
	}

	public static void demo(FileTextEvent event) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 20; i++) {
			if ((i % 3) == 0) {
				event.onChange(i);
				continue;
			}
			System.out.println("Char at " + i + " = " + sc.nextLine());
		}
	}

	public static void main(String[] args) {
		demo(new FileTextEvent() {

			@Override
			public void onChange(int i) {
				System.out.println(i);
			}

		});
	}
}
