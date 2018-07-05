package basic

object FunctionsAndImplicits {

  def main(args: Array[String]): Unit = {
    // say hello to Alice
    hello("Alice")

    // say hello to Alice without implicit
    // hello // compile error

    // say hello to Dan with implicit value
    implicit val name = "Dan"
    hello

    // say hello to Jim
    // ambiguous implicit values
    // implicit val second = "Jim" // compile error
    // hello
  }

  def hello(implicit name: String) = println(s"Hello, $name")
}
