package kr.ac.cnu.computer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class BrowserReceiveServer {
	// 외부 요청 수신 --> 지금 작성하는 자바 프로그램 (자바 애플리케이션) ---> 리스팅(서버, 데몬)
	// 자바 API --> ServerSocket API
	
	
	private static final Logger logger = Logger.getLogger(BrowserReceiveServer.class.getName());
	
	public static void main(String[] args) {
		ServerSocket server = null;
		
		
		
		/*
		 * HTTP 전송
		 * -> 응용 계층 (응용 프로그램)
		 * -> application layer
		 * -> 브라우저(크롬)
		 * -> 자바 애플리케이션 수신
		 * -> HTTP 요청 정보를 출력
		 */
		
		
		try
		{
			// TODO: Server Socket 생성 및 Client 통한 통신
			int port_num = 712; // TODO: port 번호
			server = new ServerSocket(712); // SERVER 소켓 생성
			logger.info("Server Start : " + port_num + " Port");

			// Client 하나 연결
			Socket client = server.accept();
			logger.info("Browser Connected : " + client.getInetAddress()); // 소켓이 연결 되어 있는 IP 주소를 받음

			// TODO: Client의 InputStream 값을 읽음
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			
			
			// TODO: 한 줄 씩 받은 값 한줄씩 반복하여 출력
			String line = null;
			while((line = bufferedReader.readLine()) != null)
			{
				logger.info(line);
			}
			

			logger.info("Server Shutdown");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// TODO: 서버 종료
			
			try
			{
				if(server != null)
				{
					server.close();
				}
				
			}
			catch (Exception e2) {

				
			}
		}

	}
}
