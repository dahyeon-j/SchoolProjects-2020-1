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

    // list ���
    private void printList(ListNode listNode) {
    	if (listNode == ListNode.EMPTYLIST) {
    		return;
    		}
        if (listNode == ListNode.ENDLIST) {
        	return;
        }
        // ù��° ��� ���
        printNode(listNode.car());
        // �� ���� ����Ʈ�� ���
        printList(listNode.cdr());
    }

    // Node�� ���
    private void printNode(Node node) {
    	// �ƹ� ��尡 ���� �� ����
        if (node == null)
            return;
        // node�� ListNode�� ��
        if(node instanceof ListNode){
            ListNode listNode = (ListNode) node;
            // QuoteNode�� ��
            // �� ó���� ������ ������ List -> Quote -> List���� �� ó�� List���� ����� ��
            if(((ListNode) node).car() instanceof QuoteNode) {
            	// ListNode ���
            	printList(listNode);
            } else {
            	sb.append("( "); // ���� ��ȣ �߰�
            	// Node ���
            	printList(listNode);
            	sb.append(") "); // ��� �����Ͱ� �߰��Ǹ� ������ ��ȣ �߰�            	
            }
        }
        // QuoteNode�� ��ü�� ��
        else if(node instanceof QuoteNode) {
        	QuoteNode quoteNode = (QuoteNode) node;
        	// QuoteNode ���
        	printQuoteNode(quoteNode);
        }
        else {
        	// Node�� string �߰�
            sb.append("[" + node.toString() + "] ");
        }
    }
    // QuotNode�� �� ���ڿ��� ' �߰�
    private void printQuoteNode(QuoteNode quoteNode) {
    	sb.append("\'");
    }
    

    public void prettyPrint() {
        printNode(root);
        
        System.out.println(sb);
        try (FileWriter fw = new FileWriter(OUTPUT_FILENAME);
        	PrintWriter pw = new PrintWriter(fw)) {
            pw.write(sb.toString()); // ���ڿ��� ��ȯ�� ���� ���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
