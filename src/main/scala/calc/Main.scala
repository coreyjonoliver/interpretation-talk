package calc


object Main extends App {
  val input = "(8+2*3)"
  val parser = new Parser(input.toStream)
  val expr = parser.parseExpr()
  println(expr)

  // val evaluator = new Evaluator()
  // println(evaluator.eval(expr))
}
