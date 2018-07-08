package networking;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Server {
	private int port;
	
	private String incomingMessage = "";
	JLabel chatThread;

	private ServerSocket server;
	private Socket connection;

	public Server(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) {
		Server s = new Server(8080);
		s.start();
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream());
			
			out.print("HTTP/1.1 200 \r\n");
			out.print("Content-Type: text/plain \r\n");
			out.print("Connection:close \r\n");
			out.print("\r\n");
			out.print("<button> click me </button>");
			
			out.close();
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

}
