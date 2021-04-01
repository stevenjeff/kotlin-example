/**
 * @projectName kotlinApplication
 * @description: TODO
 * @author zhangfangrui
 * @date 2021/3/2516:48
 */

fun main() {
    infixFunTest()
    varArgPrintAll("Hello", "Hallo", "Salut", "Hola", "你好")
    printAllWithPrefix(
        "Hello", "Hallo", "Salut", "Hola", "你好",
        prefix = "Greeting: "
    )
    nullSafetyTest()
}

fun log(vararg entries: String) {
    varArgPrintAll(*entries)                                             // 5
}

fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
    for (m in messages) println(prefix + m)
}

fun varArgPrintAll(vararg messages: String) {                            // 1
    for (m in messages) println(m)
}

fun infixFunTest(){
    infix fun Int.times(str: String) = str.repeat(this)        // 1
    println(5 times "Bye ")                                    // 2

    val pair = "Ferrari" to "Katrina"                          // 3
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)   // 4
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia                                       // 5
}

fun nullSafetyTest(){
    var neverNull: String = "This can't be null"            // 1
    // neverNull = null                                        // 2
    var nullable: String? = "You can keep a null here"      // 3
    nullable = null                                         // 4
    var inferredNonNull = "The compiler assumes non-null"   // 5
    // inferredNonNull = null                                  // 6
    fun strLength(notNull: String): Int {                   // 7
        return notNull.length
    }
    strLength(neverNull)                                    // 8
    //strLength(nullable)                                     // 9
}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }  // 6
}