package p1;

import java.util.Scanner;

public class P1 {

	// �� �Լ��� �ﰢ�� ���� ������ ���
	// input: �ܰ�
	static int numOfTriangle(int input) {

		if(input == 0) return 1;

		return numOfTriangle(input - 1) * 3; // ���

	}

	// �� �Լ��� ���� ���̸� ���
	// �� �Լ��� ������ ���� ����
	// input: �ܰ�
	static int length(int input) {

		if(input == 1) return 1;
		return length(input - 1) * 2; // ���
	}

	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		

		System.out.print("input : ");

		int input = scan.nextInt();

		
		// ���� ���� * �ﰢ���� ���� �� = ��� ������ ��.
		System.out.println("S" + input + " = (" + numOfTriangle(input) +"/" + length(input) + ")L");

		

	}

 

}