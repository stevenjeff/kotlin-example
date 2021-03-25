/**
 * @projectName kotlinApplication
 * @description: TODO
 * @author zhangfangrui
 * @date 2021/3/2516:48
 */

fun main() {
    infixFunTest()
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

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }  // 6
}