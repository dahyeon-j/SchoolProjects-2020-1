package p2;

import java.util.Scanner;

public class P2 {

	// �ش��ϴ� �ڸ��� ���ڸ� ����
	// ���� ���� 0,0�ڸ�.
	// ��ǥ ��
	// i�� i��° ��.
	// j: �ٿ��� ���° ��������
	static int num(int i, int j) {
		if(j == 0 || i == j) return 1;
		
		return num(i-1, j-1) + num(i-1, j); // ���
	}
	
	// ������ ����ϴ� �Լ�
	// line: n��° ���� ���ڸ� ����
	static void printLine(int line, int j) {
		if(j != 0) printLine(line, j - 1); // ���
		System.out.printf("%8d", num(line, j)); // �����Ͽ� ���	
	}
	
	// ��� ������� ����� �ִ� �Լ�
	// �ﰢ�� ���� ������ ���
	// numOfLine: �� ���� ��
	// orderOfLine: ���° ������
	static void printBlank(int numOfLine , int orderOfLine) {
		if(numOfLine> orderOfLine) printBlank(numOfLine, orderOfLine + 1); // ��� 
		
		System.out.print("    ");
	}
	
	// �ﰢ���� ����ϴ� �Լ�
	static void pascal(int input1, int input2) {
		if(input2 != 0) pascal(input1, input2 - 1); // ���
		
		printBlank(input1, input2); // ���� ���
		printLine(input2, input2); // ���� ���
		System.out.println();
		
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("input : ");
		int input = scan.nextInt();
		
		pascal(input - 1, input - 1);
	}

}
