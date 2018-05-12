package org.protokruft.example1

object newCar {
    private fun new(): Example1.Car.Builder = org.protokruft.example1.Example1.Car.newBuilder()

    operator fun invoke(fn: Example1.Car.Builder.() -> Unit): Example1.Car = new().apply(fn).build()

    fun apply(fn: Example1.Car.Builder.() -> Unit): Example1.Car = invoke(fn)

    fun let(fn: (Example1.Car.Builder) -> Unit): Example1.Car = new().apply(fn).build()
}

object newEngine {
    private fun new(): Example1.Engine.Builder = org.protokruft.example1.Example1.Engine.newBuilder()

    operator fun invoke(fn: Example1.Engine.Builder.() -> Unit): Example1.Engine = new().apply(fn).build()

    fun apply(fn: Example1.Engine.Builder.() -> Unit): Example1.Engine = invoke(fn)

    fun let(fn: (Example1.Engine.Builder) -> Unit): Example1.Engine = new().apply(fn).build()
}