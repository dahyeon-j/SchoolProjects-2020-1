package parser.ast;

import lexer.TokenType;

import java.util.HashMap;
import java.util.Map;

public class BinaryOpNode extends QuotableNodeImpl implements ValueNode {
    BinType binType;

    public BinaryOpNode(TokenType tType) {
        binType = BinType.getBinType(tType);
    }

    @Override
    public String toString() {
        return binType.name();
    }

    public enum BinType {
        MINUS {
            TokenType tokenType() {
                return TokenType.MINUS;
            }
        },
        PLUS {
            TokenType tokenType() {
                return TokenType.PLUS;
            }
        },
        TIMES {
            TokenType tokenType() {
                return TokenType.TIMES;
            }
        },
        DIV {
            TokenType tokenType() {
                return TokenType.DIV;
            }
        },
        LT {
            TokenType tokenType() {
                return TokenType.LT;
            }
        },
        GT {
            TokenType tokenType() {
                return TokenType.GT;
            }
        },
        EQ {
            TokenType tokenType() {
                return TokenType.EQ;
            }
        };

        private static Map<TokenType, BinType> fromTokenType = new HashMap<TokenType, BinType>();

        static {
            for (BinType bType : BinType.values()) {
                fromTokenType.put(bType.tokenType(), bType);
            }
        }

        static BinType getBinType(TokenType tType) {
            return fromTokenType.get(tType);
        }

        abstract TokenType tokenType();

    }

}