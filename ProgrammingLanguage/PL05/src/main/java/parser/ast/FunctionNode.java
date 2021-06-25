package parser.ast;

import java.util.HashMap;
import java.util.Map;

import lexer.TokenType;
import parser.ast.BinaryOpNode.BinType;


public class FunctionNode extends Node{
	// binaryOpNode Ŭ������ ���� �����Ͽ� �ۼ���!
	public enum FunctionType { 
		ATOM_Q 	{ TokenType tokenType() {return TokenType.ATOM_Q;} }, 
		CAR 	{ TokenType tokenType() {return TokenType.CAR;} }, 
		CDR 	{ TokenType tokenType() {return TokenType.CDR;} }, 
		COND 	{ TokenType tokenType() {return TokenType.COND;} }, 
		CONS 		{ TokenType tokenType() {return TokenType.CONS;} }, 
		DEFINE 		{ TokenType tokenType() {return TokenType.DEFINE;} },
		EQ_Q 		{ TokenType tokenType() {return TokenType.EQ_Q;} },
		LAMBDA 		{ TokenType tokenType() {return TokenType.LAMBDA;} },
		NOT 		{ TokenType tokenType() {return TokenType.NOT;} },
		NULL_Q 		{ TokenType tokenType() {return TokenType.NULL_Q;} };
		
		private static Map<TokenType, FunctionType> fromTokenType = new HashMap<TokenType, FunctionType>();
		
		static {
			/*
			 * FuctionType.values(): ���ŵ� ��� ���Ҹ� �迭�� ��� ��ȯ 
			 * fType: ��ȯ�� �迭�� ��
			 * 
			 * Map formTokenType:
			 * key: TokenType
			 * value: functionType
			 */
			for (FunctionType fType : FunctionType.values()){
				fromTokenType.put(fType.tokenType(), fType);
			}
		}
		
		/*
		 * TokenType tType�� ����Ͽ� FunctionType�� ����.
		 */
		static FunctionType getFunctionType(TokenType tType){
			return fromTokenType.get(tType);
		}
		
		abstract TokenType tokenType();
		
	}
	public FunctionType value;
	
	@Override
	public String toString(){
		// ���� ä��!
		// name(): ȣ��� ���� �̸��� String���� ����
		return value.name();
	}

	/*
	 * tType�� ����Ͽ� FuntionType�� ����.
	 * value ����
	 */
	public void setValue(TokenType tType) {
		// ���� ä��!
		FunctionType fType = FunctionType.getFunctionType(tType);
		value = fType;
	}
}
 