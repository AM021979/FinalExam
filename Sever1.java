import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Sever1{
	public static void main(String[] args) {
	int port=8888;
	int select=0;
	System.out.println("??��1||2"
			+ "1=�ȴ�����"
			+ "2=���Ҍ���");
	Scanner sc=new Scanner(System.in);
	select=sc.nextInt();
	switch(select){
	case 1:{
		startSever(port);
		break;
	}
	case 2:{
		String ip;
		System.out.println("??�댦��IP");
		ip=sc.next();
		startClient(port,ip);
		break;
	}
	}
	
}
	public static void startSever(int port){
		ServerSocket ss;
		String data;
		Scanner sc=new Scanner(System.in);
		int Out=0;
		try {//IO?��
			ss=new ServerSocket(port);
			String serverIP = InetAddress.getLocalHost().getHostAddress(); 
			System.out.println(serverIP);
			Socket ws=ss.accept();//�ȴ��B?
			System.out.println("Connection from Client IP: " + 
					ws.getInetAddress().getHostAddress());
			while(true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(ws.getInputStream()));//C��ȡ��ֵ
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(ws.getOutputStream()));//C��ȡ��ֵ
				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.println("�F�ڲ�����Ļغ�!!!");
				System.out.print("C��?��:	");
				data=br.readLine();//��C��ȡ��ֵ�G��?��
				System.out.println(data);//�@ʾ�õ���ֵ
				System.out.println("�Q��??��");//ȡ��S��?����ֵ
				data=sbr.readLine();
				bw.write(data);
				bw.newLine();
				bw.flush();
			}
			
		} catch (IOException e) {//�e�`?Ϣ
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startClient(int port,String ip){
		Scanner sc=new Scanner(System.in);
		String data;
		try {
			Socket connect=new Socket(ip,port);
			System.out.println("���B?");
			System.out.println("�����ȹ�:");
			while(true){
				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
				System.out.println("??�딵��");
				data=sbr.readLine();
				bw.write(data);//C��?��ֵ
				
				bw.newLine();
				bw.flush();
				System.out.println("S��?��");
				data=br.readLine();
				System.out.println(data);
				
				
			}
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}