import java.io.File
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @projectName kotlinApplication
 * @description: TODO
 * @author zhangfangrui
 * @date 2021/3/2511:05
 */
fun main() {
    foo(3)
    listTest()
    mapTest()
    iteratorRangeTest()
    lazyTest()
    println("Convert this to camelcase".spaceToCamelCase())
    instantiateAbstract()
    nullHandle()
    withTest()
    transform("Red")
    tryCatchTest()
    applyTest()
    tryWithTest()
    nullableTest()
    swapVal()
    calcTaxes()
}

fun foo(a: Int = 0, b: String = "fdsfsd") {
    println(b)
}

fun listTest() {
    val list = listOf(1, 2, 3, 7)
    list.filter { x -> x > 0 }
    list.filter { it > 0 }.map { it + 1 }.sortedBy { it }.forEach { println(it) }
}

fun mapTest(){
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    println(map["a"])
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

fun iteratorRangeTest(){
    for (i in 1..100) { println(i) }  // closed range: includes 100
    for (i in 1 until 100) { println(i) } // half-open range: does not include 100
    for (x in 2..10 step 2) { println(x) }
    for (x in 10 downTo 1) {
        if (x in 1..10) { println(x) }
    }
}


fun lazyTest(){
    val str by lazy(LazyThreadSafetyMode.NONE) {
        println("Init lazy")
        "Hello World"
    }
}

fun String.spaceToCamelCase(): String { return this.toLowerCase() }

/*
单例
*/
object Resource {
    val name = "Name"
}

abstract class MyAbstractClass {
    abstract fun doSomething()
    abstract fun sleep()
}

fun instantiateAbstract () {
    val myObject = object : MyAbstractClass() {
        override fun doSomething() {
            // ...
        }

        override fun sleep() { // ...
        }
    }
    myObject.doSomething()
}

fun nullHandle(){
    val files = File("Test").listFiles()
    println(files?.size) // size is printed if files is not null
    println(files?.size ?: "empty") // if files is null, this prints "empty"
    val values = mapOf("a" to 1)
    val email = values["a"] ?: throw IllegalStateException("Email is missing!")
    values?.let {
        println(it)
    }
    //val mainEmail = values.firstOrNull() ?: ""
}
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

fun tryCatchTest() {
    val result = try {
        println()
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }

    // Working with result
}

fun foo(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}

fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

fun theAnswer1() = 42

fun theAnswer2(): Int {
    return 42
}

fun withTest(){
    class Turtle {
        fun penDown(){}
        fun penUp(){}
        fun turn(degrees: Double){}
        fun forward(pixels: Double){}
    }

    val myTurtle = Turtle()
    with(myTurtle) {
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}

class Rectangle(var height: Double, var length: Double) {
    var perimeter = (height + length) * 2
}

fun applyTest(){
    val myRectangle = Rectangle(4.0,6.0).apply {
        length = 3.7
    }
}

fun tryWithTest(){
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}

fun nullableTest(){
    val b: Boolean? = null
    if (b == true) {
        println(1)
    } else {
        println(2)
    }
}

fun swapVal(){
    var a = 1
    var b = 2
    a = b.also { b = a }
}

fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")









