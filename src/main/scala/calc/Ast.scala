package calc

sealed trait Expr
case class Numeral(n: Int) extends Expr
case class BinOpExpr(e1: Expr, bop: BinOp, e2: Expr) extends Expr


sealed trait BinOp
case object Plus extends BinOp
case object Minus extends BinOp
case object Times extends BinOp
case object Divide extends BinOp