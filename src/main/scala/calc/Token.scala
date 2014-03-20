package calc

trait Token

case object LEX_PLUS extends Token
case object LEX_MINUS extends Token
case object LEX_TIMES extends Token
case object LEX_DIVIDE extends Token
case object LEX_LPAREN extends Token
case object LEX_RPAREN extends Token
case object LEX_EOS extends Token // end-of-source

case class LEX_NUMERAL(value: Int) extends Token