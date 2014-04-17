package calc


object Main extends App {
  val input = "2*3+8"
  val parser = new Parser(input.toStream)
  val expr = parser.parseExpr()
  println(expr)

  val evaluator = new Evaluator()
  println(evaluator.eval(expr))
}