import java.net.*;
public class Main {
	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName("www.ufc.br");
			System.out.println("IP: "+ ip.toString());
		}
		catch (UnknownHostException e) {
			System.out.println("Unknown address");
		}
	}
}