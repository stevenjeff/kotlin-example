import java.util.*

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
    rangeForTest()
    equalityTest()
    println(max(99, -42))
    dataClassTest()
    enumTest()
    println(sealedClassTest(Cat("Snowy")))
    classTest()
    objectTest(10, 2, 1)
    functionsTest()
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

fun rangeForTest(){
    for(i in 0..3) {             // 1
        print(i)
    }
    print(" ")

    for(i in 0 until 3) {        // 2
        print(i)
    }
    print(" ")

    for(i in 2..8 step 2) {      // 3
        print(i)
    }
    print(" ")

    for (i in 3 downTo 0) {      // 4
        print(i)
    }
    print(" ")

    for (c in 'a'..'d') {        // 1
        print(c)
    }
    print(" ")

    for (c in 'z' downTo 's' step 2) { // 2
        print(c)
    }
    print(" ")
    val x = 2
    if (x in 1..5) {            // 1
        print("x is in range from 1 to 5")
    }
    println()

    if (x !in 6..10) {          // 2
        print("x is not in range from 6 to 10")
    }
}

fun equalityTest(){
    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")

    println(authors == writers)   // true 不会考虑顺序
    println(authors === writers)  // false 不同引用对象
}

fun max(a: Int, b: Int) = if (a > b) a else b         // 1

data class User(val name: String, val id: Int)             // 1

fun dataClassTest(){
    val user = User("Alex", 1)
    println(user)                                          // 2
    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)
    println("user == secondUser: ${user == secondUser}")   // 3
    println("user == thirdUser: ${user == thirdUser}")
    println(user.hashCode())                               // 4
    println(secondUser.hashCode())
    // copy() function
    println(user.copy())                                   // 5
    println(user.copy("Max"))                              // 6
    println(user.copy(id = 2))                             // 7
    println("name = ${user.component1()}")                 // 8
    println("id = ${user.component2()}")
}

enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}

fun enumTest(){
    val state = State.RUNNING                         // 2
    val message = when (state) {                      // 3
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }
    println(message)
}

sealed class Mammal(val name: String)                                                   // 1

class Cat(val catName: String) : Mammal(catName)                                        // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun sealedClassTest(mammal: Mammal): String {
    when (mammal) {                                                                     // 3
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5
    }                                                                                   // 6
}

class LuckDispatcher {                    //1
    fun getNumber() {                     //2
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}

fun classTest(){
    val d1 = LuckDispatcher()             //3
    val d2 = LuckDispatcher()
    d1.getNumber()                        //4
    d2.getNumber()
}

object DoAuth {                                                 //1
    fun takeParams(username: String, password: String) {        //2
        println("input Auth parameters = $username:$password")
    }
}

class BigBen {                                  //1
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}

fun objectTest(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

    print("Total price: $$total")                                               //4

    DoAuth.takeParams("foo", "qwerty")

    BigBen.getBongs(3)
}

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {  // 1
    return operation(x, y)                                          // 2
}

fun sum(x: Int, y: Int) = x + y                                     // 3

fun operation(): (Int) -> Int {                                     // 1
    return ::square
}

fun square(x: Int) = x * x

fun functionsTest(){
    val sumResult = calculate(4, 5, ::sum)                          // 4
    val mulResult = calculate(4, 5) { a, b -> a * b }               // 5
    println("sumResult $sumResult, mulResult $mulResult")
    val func = operation()                                          // 3
    println(func(2))                                                // 4
    lambdaFunctionTest()
    extendFunctionTest()
}

fun lambdaFunctionTest(){
    val upperCase1: (String) -> String = { str: String -> str.toUpperCase() } // 1
    val upperCase2: (String) -> String = { str -> str.toUpperCase() }         // 2
    val upperCase3 = { str: String -> str.toUpperCase() }                     // 3
// val upperCase4 = { str -> str.toUpperCase() }                          // 4
    val upperCase5: (String) -> String = { it.toUpperCase() }                 // 5
    val upperCase6: (String) -> String = String::toUpperCase                  // 6
    println(upperCase1("hello"))
    println(upperCase2("hello"))
    println(upperCase3("hello"))
    println(upperCase5("hello"))
    println(upperCase6("hello"))
}

data class Item(val name: String, val price: Float)                                         // 1
data class Order(val items: Collection<Item>)
fun Order.maxPricedItemValue(): Float = this.items.maxByOrNull { it.price }?.price ?: 0F    // 2
fun Order.maxPricedItemName() = this.items.maxByOrNull { it.price }?.name ?: "NO_PRODUCTS"
val Order.commaDelimitedItemNames: String                                                   // 3
    get() = items.map { it.name }.joinToString()

fun extendFunctionTest(){
    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))
    println("Max priced item name: ${order.maxPricedItemName()}")                           // 4
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")                                      // 5
    println(null.nullSafeToString())
    println("Kotlin".nullSafeToString())
}

fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"  // 1


