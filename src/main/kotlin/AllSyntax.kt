import java.lang.Math.abs
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
    listCollectionTest()
    setCollectionList()
    mapCollectionList()
    filterTest()
    mapFunctionTest()
    anyTest()
    allTest()
    noneTest()
    findTest()
    firstOrNullAndLastOrNullTest()
    countTest()
    groupByAssociateBy()
    partitionTest()
    flatMapTest()
    minOrNullMaxOrNullTest()
    sortedTest()
    mapCollectionTest()
    zipFunctionTest()
    getOrElseTest()
    letTest()
    runTest()
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
// lambda function
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
// extend function
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

//list
val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // 1
val sudoers: List<Int> = systemUsers                              // 2

fun addSystemUser(newUser: Int) {                                 // 3
    systemUsers.add(newUser)
}

fun getSysSudoers(): List<Int> {                                  // 4
    return sudoers
}

fun listCollectionTest(){
    addSystemUser(4)                                              // 5
    println("Tot sudoers: ${getSysSudoers().size}")               // 6
    getSysSudoers().forEach {                                     // 7
            i -> println("Some useful info on user $i")
    }
    // getSysSudoers().add(5) <- Error!                           // 8
}

val openIssues: MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2", "uniqueDescr3") // 1

fun addIssue(uniqueDesc: String): Boolean {
    return openIssues.add(uniqueDesc)                                                             // 2
}

fun getStatusLog(isAdded: Boolean): String {
    return if (isAdded) "registered correctly." else "marked as duplicate and rejected."          // 3
}

fun setCollectionList(){
    val aNewIssue: String = "uniqueDescr4"
    val anIssueAlreadyIn: String = "uniqueDescr2"

    println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")                              // 4
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")                // 5
}

const val POINTS_X_PASS: Int = 15
val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // 1
val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // 2

fun updatePointsCredit(accountId: Int) {
    if (EZPassAccounts.containsKey(accountId)) {                                        // 3
        println("Updating $accountId...")
        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS  // 4
    } else {
        println("Error: Trying to update a non-existing account (id: $accountId)")
    }
}

fun accountsReport() {
    println("EZ-Pass report:")
    EZPassReport.forEach {                                                              // 5
            k, v -> println("ID $k: credit $v")
    }
}

fun mapCollectionList(){
    accountsReport()                                                                    // 6
    updatePointsCredit(1)                                                               // 7
    updatePointsCredit(1)
    updatePointsCredit(5)                                                               // 8
    accountsReport()                                                                    // 9
}

fun filterTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1
    val positives = numbers.filter { x -> x > 0 }  // 2
    val negatives = numbers.filter { it < 0 }      // 3
}

fun mapFunctionTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)     // 1
    val doubled = numbers.map { x -> x * 2 }      // 2
    val tripled = numbers.map { it * 3 }          // 3
}

fun anyTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val anyNegative = numbers.any { it < 0 }             // 2
    val anyGT6 = numbers.any { it > 6 }                  // 3
    println("Numbers: $numbers")
    println("Is there any number less than 0: $anyNegative")
    println("Is there any number greater than 6: $anyGT6")
}

fun allTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val allEven = numbers.all { it % 2 == 0 }            // 2
    val allLess6 = numbers.all { it < 6 }                // 3
    println("Numbers: $numbers")
    println("All numbers are even: $allEven")
    println("All numbers are less than 6: $allLess6")
}

fun noneTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val allEven = numbers.none { it % 2 == 1 }           // 2
    val allLess6 = numbers.none { it > 6 }               // 3
    println("Numbers: $numbers")
    println("All numbers are even: $allEven")
    println("No element greater than 6: $allLess6")
}

fun findTest(){
    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // 1
    val first = words.find { it.startsWith("some") }                                // 2
    val last = words.findLast { it.startsWith("some") }                             // 3
    val nothing = words.find { it.contains("nothing") }                             // 4
}

fun firstOrNullAndLastOrNullTest(){
    val words = listOf("foo", "bar", "baz", "faz")         // 1
    val empty = emptyList<String>()                        // 2
    val first = empty.firstOrNull()                        // 3
    val last = empty.lastOrNull()                          // 4
    val firstF = words.firstOrNull { it.startsWith('f') }  // 5
    val firstZ = words.firstOrNull { it.startsWith('z') }  // 6
    val lastF = words.lastOrNull { it.endsWith('f') }      // 7
    val lastZ = words.lastOrNull { it.endsWith('z') }      // 8
    println("First $first, last $last")
    println("First starts with 'f' is $firstF, last starts with 'z' is $firstZ")
    println("First ends with 'f' is $lastF, last ends with 'z' is $lastZ")
}

fun countTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val totalCount = numbers.count()                     // 2
    val evenCount = numbers.count { it % 2 == 0 }        // 3
    println("Total number of elements: $totalCount")
    println("Number of even elements: $evenCount")
}

fun groupByAssociateBy(){
    data class Person(val name: String, val city: String, val phone: String) // 1
    val people = listOf(                                                     // 2
        Person("John", "Boston", "+1-888-123456"),
        Person("Sarah", "Munich", "+49-777-789123"),
        Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
        Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"))
    val phoneBook = people.associateBy { it.phone }                          // 3
    val cityBook = people.associateBy(Person::phone, Person::city)           // 4
    val peopleCities = people.groupBy(Person::city, Person::name)            // 5
    val lastPersonCity = people.associateBy(Person::city, Person::name)      // 6
    println("People: $people")
    println("Phone book: $phoneBook")
    println("City book: $cityBook")
    println("People living in each city: $peopleCities")
    println("Last person living in each city: $lastPersonCity")
}

fun partitionTest(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)                // 1
    val evenOdd = numbers.partition { it % 2 == 0 }           // 2
    val (positives, negatives) = numbers.partition { it > 0 } // 3
    println("Numbers: $numbers")
    println("Even numbers: ${evenOdd.first}")
    println("Odd numbers: ${evenOdd.second}")
    println("Positive numbers: $positives")
    println("Negative numbers: $negatives")
}

fun flatMapTest(){
    val fruitsBag = listOf("apple","orange","banana","grapes")  // 1
    val clothesBag = listOf("shirts","pants","jeans")           // 2
    val cart = listOf(fruitsBag, clothesBag)                    // 3
    val mapBag = cart.map { it }                                // 4
    val flatMapBag = cart.flatMap { it }                        // 5

    println("Your bags are: $mapBag")
    println("The things you bought are: $flatMapBag")
}

fun minOrNullMaxOrNullTest(){
    val numbers = listOf(1, 2, 3)
    val empty = emptyList<Int>()
    val only = listOf(3)
    println("Numbers: $numbers, min = ${numbers.minOrNull()} max = ${numbers.maxOrNull()}") // 1
    println("Empty: $empty, min = ${empty.minOrNull()}, max = ${empty.maxOrNull()}")        // 2
    println("Only: $only, min = ${only.minOrNull()}, max = ${only.maxOrNull()}")            // 3
}

fun sortedTest(){
    val shuffled = listOf(5, 4, 2, 1, 3, -10)                   // 1
    val natural = shuffled.sorted()                             // 2
    val inverted = shuffled.sortedBy { -it }                    // 3
    val descending = shuffled.sortedDescending()                // 4
    val descendingBy = shuffled.sortedByDescending { abs(it)  } // 5
    println("Shuffled: $shuffled")
    println("Natural order: $natural")
    println("Inverted natural order: $inverted")
    println("Inverted natural order value: $descending")
    println("Inverted natural order of absolute values: $descendingBy")
}

fun mapCollectionTest(){
    val map = mapOf("key" to 42)
    val value1 = map["key"]                                     // 1
    val value2 = map["key2"]                                    // 2
    val value3: Int = map.getValue("key")                       // 1
    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("key2")                // 3
    try {
        map.getValue("anotherKey")                              // 4
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }
    println("value1 is $value1")
    println("value2 is $value2")
    println("value3 is $value3")
    println("value4 is $value4")
}

fun zipFunctionTest(){
    val A = listOf("a", "b", "c","d")                  // 1
    val B = listOf(1, 2, 3, 4,5)// 1
    val resultPairs = A zip B                      // 2
    val resultReduce = A.zip(B) { a, b -> "$a$b" } // 3
    println("A to B: $resultPairs")
    println("\$A\$B: $resultReduce")
}

fun getOrElseTest(){
    val list = listOf(0, 10, 20)
    println(list.getOrElse(1) { 42 })    // 1
    println(list.getOrElse(10) { 42 })   // 2

    val map = mutableMapOf<String, Int?>()
    println(map.getOrElse("x") { 1 })       // 1

    map["x"] = 3
    println(map.getOrElse("x") { 1 })       // 2

    map["x"] = null
    println(map.getOrElse("x") { 1 })       // 3
}

fun customPrint(s: String) {
    print(s.toUpperCase())
}

fun letTest(){
    val empty = "test".let {               // 1
        customPrint(it)                    // 2
        it.isEmpty()                       // 3
    }
    println(" is empty: $empty")
    fun printNonNull(str: String?) {
        println("Printing \"$str\":")
        str?.let {                         // 4
            print("\t")
            customPrint(it)
            println()
        }
    }
    printNonNull(null)
    printNonNull("my string")
}

fun runTest(){
    fun getNullableLength(ns: String?) {
        println("for \"$ns\":")
        ns?.run {                                                  // 1
            println("\tis empty? " + this.isEmpty())                    // 2
            println("\tlength = $length")
            length                                                 // 3
        }
    }
    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with Kotlin")
}