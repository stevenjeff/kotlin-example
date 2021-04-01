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
    // generic test
    genericTest()
    val stack = mutableStackOf(0.62, 3.14, 2.7)
    println(stack)
    inheritanceTest()
    passConstructParamTest()
    whenTest()
    allLoopTest()
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

fun genericTest(){
    val stack = MutableStack(0.62, 3.14, 2.7)
    stack.push(9.87)
    println(stack)

    println("peek(): ${stack.peek()}")
    println(stack)

    for (i in 1..stack.size()) {
        println("pop(): ${stack.pop()}")
        println(stack)
    }
}

fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

class MutableStack<E>(vararg items: E) {              // 1

    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)        // 2

    fun peek(): E = elements.last()                     // 3

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}

open class Dog {                // 1
    open fun sayHello() {       // 2
        println("wow wow!")
    }
}

class Yorkshire : Dog() {       // 3
    override fun sayHello() {   // 4
        println("wif wif!")
    }
}

fun inheritanceTest(){
    val dog: Dog = Yorkshire()
    dog.sayHello()
}

open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}

class Asiatic(name: String) : Lion(name = name, origin = "India") // 1

fun passConstructParamTest() {
    val lion: Lion = Asiatic("Rufo")                              // 2
    lion.sayHello()
}

fun whenTest() {
    cases("Hello")
    cases(1)
    cases(0L)
    cases(Asiatic("Rufo"))
    cases("hello")
}

fun cases(obj: Any) {
    when (obj) {                                     // 1
        1 -> println("One")                          // 2
        "Hello" -> println("Greeting")               // 3
        is Long -> println("Long")                   // 4
        !is String -> println("Not a string")        // 5
        else -> println("Unknown")                   // 6
    }
}

fun allLoopTest(){
    forTest()
    whileTest()
    iteratorTest()
}

fun forTest(){
    val cakes = listOf("carrot", "cheese", "chocolate")

    for (cake in cakes) {                               // 1
        println("Yummy, it's a $cake cake!")
    }
}

fun whileTest(){
    fun eatACake() = println("Eat a Cake")
    fun bakeACake() = println("Bake a Cake")

    fun main(args: Array<String>) {
        var cakesEaten = 0
        var cakesBaked = 0
        while (cakesEaten < 5) {                    // 1
            eatACake()
            cakesEaten ++
        }
        do {                                        // 2
            bakeACake()
            cakesBaked++
        } while (cakesBaked < cakesEaten)

    }
}

class Animal(val name: String)

class Zoo(val animals: List<Animal>) {

    operator fun iterator(): Iterator<Animal> {             // 1
        return animals.iterator()                           // 2
    }
}

fun iteratorTest() {
    val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))
    for (animal in zoo) {                                   // 3
        println("Watch out, it's a ${animal.name}")
    }

}