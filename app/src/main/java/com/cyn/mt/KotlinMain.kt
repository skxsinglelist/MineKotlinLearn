package com.cyn.mt

import android.content.Context
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar


infix fun String.beginWith(str: String) = startsWith(str)
inline fun <reified T> startActivity(context:Context,block: Intent.() -> Unit){
    val intent = Intent(context,T::class.java)
    intent.block()
    context.startActivity(intent)
}

class KotlinMain {


}

fun main() {

    val letterCount = "siunnhj807gb9999".letterCount("qwe")
    println("letterCount = $letterCount")

    val list = mutableListOf("name", "1", "a", 1)
    list.forEach {
        println(it)
    }


    val map = mutableMapOf("1" to 1, "2" to 2)
    map.forEach {
        println(it.key + " " + it.value)
    }
    for ((mapKey, mapValue) in map) {
        println("$mapKey $mapValue")
    }


    num1AndNum2(10, 20) { n1: Int, n2: Int ->
        n1 + n2
    }

    val strBuild = StringBuilder()
    strBuild.build {
        append("a")
    }

    println("Atisz" beginWith "A")

}


fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result + 6
}

fun add(num1: Int, num2: Int) = num1 + num2

fun String.letterCount(str: String): Int {

    var count = 0;
    for (c in this + str) {
        if (c.isLetter()) {
            count++
        }
    }
    return count
}

//高阶扩展函数
//inline fun StringBuilder.build(block:StringBuilder.()->Unit):StringBuilder{
//    block()
//    return this
//}

inline fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}

fun View.showSnackBar(
    text: String, textAction: String? = null, duration: Int = Snackbar.LENGTH_LONG,
    block: (View.() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, text, duration)
    if (textAction != null && block != null) {
        snackbar.setAction(textAction) {
            this.block()
        }
    }
    snackbar.show()
}





