package calc

class Parser(private var input: Stream[Char]) {

  private val lexer = new Lexer(input)

  // E -> T `PLUS` E | T `MINUS` E | T
  def parseExpr(): Expr = {
    val t = parseTerm()
    lexer.peek() match {
      case LEX_PLUS => { lexer.next(); BinOpExpr(t, Plus, parseExpr()) }
      case LEX_MINUS => { lexer.next(); BinOpExpr(t, Minus, parseExpr()) }
      case _ => t
    }
  }

  // T -> F `TIMES` T | F `DIVIDE` T | F
  def parseTerm(): Expr = {
    val f = parseFactor()
    lexer.peek() match {
      case LEX_TIMES => { lexer.next(); BinOpExpr(f, Times, parseTerm()) }
      case LEX_DIVIDE => { lexer.next(); BinOpExpr(f, Divide, parseTerm()) }
      case _ => f
    }
  }

  // F -> `NUMERAL` | `LPAREN`E`RPAREN`
  def parseFactor(): Expr = {
    lexer.peek() match {
      case LEX_NUMERAL(n) => { lexer.next(); Numeral(n) }
      case LEX_LPAREN => {
        lexer.eatLPAREN()
        val e = parseExpr()
        lexer.eatRPAREN()
        e
      }
    }
  }
}
