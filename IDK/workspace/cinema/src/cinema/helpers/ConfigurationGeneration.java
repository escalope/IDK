package cinema.helpers;

public class ConfigurationGeneration {

	public static void main(String[] args) {
		System.out.println("<vector-array>");
		for (int j=0;j<10;j++){
			System.out.println("<vector>");
			for (int k=0;k<100;k++){
				System.out.println("<string>InterfaceAgent_"+k+"</string>");
			}
			System.out.println("</vector>");
		}
		System.out.println("</vector-array>");
	}
}
