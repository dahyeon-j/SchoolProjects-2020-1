package parser.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

import parser.ast.*;
import parser.ast.BinaryOpNode.BinType;
import lexer.Scanner;
import lexer.ScannerMain;
import lexer.Token;
import lexer.TokenType;

public class CuteParser {
	private Iterator<Token> tokens;

	public CuteParser(File file) {
		try {
			tokens = Scanner.scan(file); // lexer���� ������ Scanner�� �̿��ؼ� ��ūȭ�� �� ��
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Token getNextToken() {
		// ���� ��ū�� ���ٸ� null�� ����
		if (!tokens.hasNext())
			return null;
		// ���� ��ū�� �ִٸ� ���� ��ū�� ����
		return tokens.next();
	}

	// ��ū���� � Ÿ���� ������� �����ش� 
	public Node parseExpr() {
		// ���� ��ū
		Token t = getNextToken();
		// ��ū�� ���ٸ� null�� ����
		if (t == null) {
			System.out.println("No more token");
			return null;
		}
		TokenType tType = t.type();
		String tLexeme = t.lexme();

		switch (tType) {
		case ID:
			IdNode idNode = new IdNode();
			idNode.value = tLexeme;
			return idNode;
		case INT:
			IntNode intNode = new IntNode();
			if (tLexeme == null)
				System.out.println("???");
			intNode.value = new Integer(tLexeme);
			return intNode;

		// BinaryOpNode�� ���Ͽ� �ۼ�
		// +, -, /, *�� �ش�
		case DIV:
		case EQ:
		case MINUS:
		case GT:
		case PLUS:
		case TIMES:
		case LT:
			BinaryOpNode binaryOpNode = new BinaryOpNode();
			binaryOpNode.setValue(tType); // tType�� ����Ͽ� BinType ����
			return binaryOpNode;
		

		// FunctionNode�� ���Ͽ� �ۼ�
		// Ű���尡 FunctionNode�� �ش�
		case ATOM_Q:
		case CAR:
		case CDR:
		case COND:
		case CONS:
		case DEFINE:
		case EQ_Q:
		case LAMBDA:
		case NOT:
		case NULL_Q:
			/*
			���� ä��!
			 */
			FunctionNode functionNode = new FunctionNode();
			functionNode.setValue(tType);
			return functionNode;

		// BooleanNode�� ���Ͽ� �ۼ�
		case FALSE:
			BooleanNode falseNode = new BooleanNode();
			falseNode.value = false;
			return falseNode;
		case TRUE:
			BooleanNode trueNode = new BooleanNode();
			trueNode.value = true;
			return trueNode;

		// case L_PAREN�� ���� case R_PAREN�� ��쿡 ���ؼ� �ۼ�
		// L_PAREN�� ��� parseExprList()�� ȣ���Ͽ� ó��
		// ���� ��ȣ
		case L_PAREN:
			/*
			���� ä��!
			 */
			ListNode listNode = new ListNode();
			listNode.value = parseExprList(); // Node�� ����Ʈ ����
			return listNode; // list ����

		case R_PAREN:
			return null;

		default:
			// head�� next�� ����� head�� ��ȯ�ϵ��� �ۼ�
			System.out.println("Parsing Error!");
			return null;
		}

	}

	// List�� value�� �����ϴ� �޼ҵ�
	private Node parseExprList() {
		Node head = parseExpr(); // parseExpr �̿��Ͽ� � Ÿ���� Node����
		// head�� next ��带 set�Ͻÿ�.
		if (head == null) // if next token is RPAREN
			return null;
		head.setNext(parseExprList()); // ���� ��忡 ���Ͽ� parseExprList ����.
		return head; // ��� ����
	}
}
