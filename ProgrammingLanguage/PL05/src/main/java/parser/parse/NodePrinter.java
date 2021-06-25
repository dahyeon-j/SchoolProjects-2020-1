package parser.parse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import parser.ast.*;

public class NodePrinter {
    private final String OUTPUT_FILENAME = "output06.txt";
    private StringBuffer sb = new StringBuffer();
    private Node root;

    public NodePrinter(Node root) {
        this.root = root;
    }

    // ��ȣ�� �������
    private void printList(Node head) {
        if (head == null) {
            sb.append("( )");
            return;
        }
        sb.append("("); // ���� ��ȣ �߰�
        printNode(head); // ��ȣ �ȿ� ���� �߰�
        sb.append(")"); // ��� �����Ͱ� �߰��Ǹ� ������ ��ȣ �߰�
    }

    private void printNode(Node head) {
    	// �ƹ� ��尡 ���� �� ����
        if (head == null)
            return;

        // ListNode�� �� -> "("�� ������ ��
        if (head instanceof ListNode) {
            ListNode ln = (ListNode) head;
            printList(ln.value);

        } else {
        	// ����� ���� ����
            sb.append("[" + head + "]");
        }

        // ��尡 ���� ��
        if (head.getNext() != null) {
            sb.append(" ");
        }
        // ���� ��带 ���
        printNode(head.getNext());
    }

    public void prettyPrint() {
        printNode(root);

        try (FileWriter fw = new FileWriter(OUTPUT_FILENAME);
        	PrintWriter pw = new PrintWriter(fw)) {
            pw.write(sb.toString()); // ���ڿ��� ��ȯ�� ���� ���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
