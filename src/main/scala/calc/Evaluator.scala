package calc

// "(8+2*3)"
class Evaluator() {
  def eval(expr: Expr): Int = {
   expr match {
     case BinOpExpr(e1, op, e2) =>
       op match {
         case Plus => eval(e1) + eval(e2)
         case Minus => eval(e1) - eval(e2)
         case Times => eval(e1) * eval(e2)
         case Divide => eval(e1) / eval(e2)
       }
     case Numeral(n) => n
   }
  }
}
