package org.protokruft

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import org.junit.Test
import org.protokruft.GenerateProtobufServiceDsl.generate
import org.protokruft.example1.CarServiceGrpc
import org.protokruft.example1.Example1.Car
import org.protokruft.example1.Example1.Engine
import org.protokruft.example2.Example2.Address
import org.protokruft.example2.Example2.Person
import org.protokruft.example2.PersonServiceGrpc
import org.protokruft.example3.CatServiceGrpc
import org.protokruft.example3.Example3.Cat
import org.protokruft.example3.Example3.Leg

class GenerateProtobufServiceDslTest {
    @Test
    fun `generates expected output for multiple packages`() {
        val generated = generate({
            listOf(
                    GrpcService(asClassName<CarServiceGrpc>(), listOf(GrpcMethod("getEngine", listOf(asClassName<Car>()), asClassName<Engine>()))),
                    GrpcService(asClassName<PersonServiceGrpc>(), listOf(GrpcMethod("getAddress", listOf(asClassName<Person>()), asClassName<Address>()))),
                    GrpcService(asClassName<CatServiceGrpc>(), listOf(GrpcMethod("getLeg", listOf(asClassName<Cat>()), asClassName<Leg>())))
            )
        }, "name", "")
        assertThat(generated.size, equalTo(3))
        check(1, generated, "expectedService")
        check(2, generated, "expectedService")
        check(3, generated, "expectedService")
    }


    @Test
    fun `generates expected output for a marker interface`() {
        val generated = generate({
            listOf(
                    GrpcService(asClassName<CarServiceGrpc>(), listOf(GrpcMethod("getEngine", listOf(asClassName<Car>()), asClassName<Engine>())))
            )
        },
                outputFilename = "name",
                serviceDslSuffix = "",
                markerInterface = "com.somecompany.yourcode.GrpcServiceMarker"
        )
        assertThat(generated.size, equalTo(1))
        check(1, generated, "expectedCarServiceWithMarkerInterface")
    }
}

inline fun <reified T> asClassName(): ClassName = T::class.asClassName()