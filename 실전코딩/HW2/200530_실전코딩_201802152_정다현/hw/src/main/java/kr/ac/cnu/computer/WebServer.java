package kr.ac.cnu.computer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class WebServer {
	private static final Logger logger = Logger.getLogger(BrowserReceiveServer.class.getName());
	
	public static void main(String[] args) {
		ServerSocket server = null;

		try
		{
			int portNum = 712; // TODO: 포트 번호 지정
			// TODO: Server Socket 지정된 포트로 생성
			logger.info("Server Start : "+portNum+" Port");
			
			// 서버 소켓 생성
			server = new ServerSocket(portNum);

			// client 접속이 계속 일어나게 하기 위해
			// 연속적인 Client의 접속이 가능
			while (true)
			{
				// TODO: Client 지정
				Socket client = server.accept();
				// getInetAddress: 소켓의 IP 주소를 가져옴
				logger.info("Browser Connected : " + client.getInetAddress());
				
				
				// TODO: Response의 start 함수 실행
				// 접속을 Thread 형태로 만듬 -> Response extends Thread
				// 스레드를 생성하여 Response 객체에 응답처리를 위임
				new Response(client).start(); // run(): 직접 호출 불가능 (스레드 스케줄러를 사용자 임의로 호출 불가) -> start()를 호출
							}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			// TODO: 서버 종료
			try
			{
				if(server!= null) // 서버가 생성되어 있는지 검사
				{
					server.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}
}
