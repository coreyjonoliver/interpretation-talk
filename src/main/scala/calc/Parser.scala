package calc

class Parser(private var input: Stream[Char]) {

  private val lexer = new Lexer(input)

  // E -> ABE | A
  def parserExpr(): Expr = {
    ???
  }

  // A -> `NUMERAL` | `LPAREN`E`RPAREN`
  // B -> `PLUS` | `MINUS` | `TIMES` | `DIVIDE`
  def parseAtom(): Expr = {
    ???
  }
}
