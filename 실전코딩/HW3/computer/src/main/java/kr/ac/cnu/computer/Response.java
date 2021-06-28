package kr.ac.cnu.computer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;

public class Response extends Thread {
	private Socket client;

	public Response(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;

		// 클라이언트 소켓으로 부터 정보를 읽어옴
		// 예외가 발생할 가능성이 높은 코드들은 try catch 문 안에 삽입
		try {
			// TODO:
			bufferedReader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			String line = bufferedReader.readLine(); // 소켓으로 부터 HTTP 요청 정보 해석 <--- 헤더(헤더 정보 가운데  가장 첫 줄만 추출) + 바디
			
			
			// HTTP 요청 정보를 추출
			String url = getUrl(line); // url 추출
			String uri = getUri(url); // uri 추출
			// 정적 자원 로딩(HTTP 응답 정보)
			// HTTP 응답 정보 : 헤더 + 바디 (정적 자원)
			String staticResource = getStaticResource(uri); // 요청 정보에 맞는 응답 자원을 찾고 응답 자원을 생성
			// contents의 총 길이
			String responseHeaders = getResponseHeaders(staticResource.getBytes().length);

			
			
			
			printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			// HTTP 정보 완성
			printWriter.write(responseHeaders); // 헤더 정보
			printWriter.write("\r\n"); // 헤더와 바디 사이에 반드시 한 줄을 추가 해야 함
			printWriter.write(staticResource); // 바디 정보
			printWriter.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				/*
				 * closing을 할 때에는 역순으로
				 * 
				 * 
				 * printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
				 * 위의 줄을 보면 client -> bufferedReader -> printWriter순서대로 생성된 것을 알 수 있음
				 * 
				 * printWriter -> bufferedReader -> client 순으로 종료
				 */
				if (printWriter != null) { // printWrite 객체가 있는지 검사
					printWriter.close();
				}

				if (bufferedReader != null) { // bufferedReader 객체가 있는지 검사
					bufferedReader.close();
				}

				if (this.client != null) { // client 객체가잇는지 검사
					client.close();
				}
			} catch (Exception e) {
			}
		}
	}

	// html 파일이 저장되어 있는 경로명
	private static final String WEB_ROOT = "C:\\project";

	// String : 한번 생성되면 변경 불가 -> 변경시 새로운 객체 생성
	// StringBuffer: 문자열을 추가하거나 변경할 때 주로 사용하는 자료형
	public String getResponseHeaders(int size) {
		StringBuffer headers = new StringBuffer("");

		// TODO: 과제 문서에 나온 양식대로 응답 생성
		headers.append("HTTP/1.1 200 OK\r\n");
		headers.append("HOST: localhost\r\n");
		headers.append("Content-Length: " + size + "\r\n");
		headers.append("Content-Type: text/html;charset=UTF-8\r\n");
		
		
		return headers.toString(); // 응답 header 리턴
	}

	/**
	 * 
	 * @param line
	 * 		line: GET /index.html?no=100 HTTP/1.1 의 형태
	 * 		공백으로 분리
	 * 		0. GET
	 * 		1. /index.html?no=100 -> 이게 필요 
	 * 		2. HTTP/1.1
	 * @return 1 번째 인덱스를 출력
	 */
	// HTTP 요청 정보 (헤더<첫 번째 중에 'GET'> +바디)
	public String getUrl(String line) {
		return line.split("[ ]")[1]; // 공백으로 분리하여 1번째 data 리턴
	}
	
	/**
	 * 
	 * @param url
	 * url: http://localhost:712/shop/hello.html -> ? 뒤에 추가 될수도안될 수도 있음
	 * 
	 * 제일 마지막 / 부터의 ? 또는 끝까지의 정보를 출력
	 * 
	 * @return uri
	 */

	public String getUri(String url) {
		System.out.println("---->" + url);
		
		// 주소에 ? 가 없을 때
		// ex: http://localhost:712/shop/hello.html
		if(url.lastIndexOf("?") == -1) return url.substring(url.lastIndexOf("/") + 1);
		// 주소에 ?가 있어 추가적인 정보가 더 있을 때
		// ex: http://localhost:712/shop/hello.html?num=201802152
		return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"));
	}

	public String getStaticResource(String uri) throws IOException {
		StringBuffer html = new StringBuffer(""); // 응답 자원

		// 자바 파일 I/O API <-- 특정 위치에 있는 정적 자원을 로딩 (메모리로)
		// try (요청 정보에 맞는 응답자원을 찾는다)
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(WEB_ROOT, uri).toFile()))) {
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				html.append(line).append("\r\n"); // 응답 자원을 생성
			}
		}

		return html.toString();
	}
}
