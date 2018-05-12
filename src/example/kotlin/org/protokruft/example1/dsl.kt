package org.protokruft.example1

fun car(fn: Example1.Car.Builder.() -> Unit): Example1.Car = org.protokruft.example1.Example1.Car.newBuilder().apply(fn).build()

fun engine(fn: Example1.Engine.Builder.() -> Unit): Example1.Engine = org.protokruft.example1.Example1.Engine.newBuilder().apply(fn).build()
