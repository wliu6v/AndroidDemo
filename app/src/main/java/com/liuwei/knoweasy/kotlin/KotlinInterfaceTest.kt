package com.liuwei.knoweasy.kotlin

import com.liuwei.knoweasy.kotlin.JavaInterface.*

/**
 * Created by liuwei on 2017/9/22.
 */
class KotlinInterfaceTest {

    fun test() {

        // 1. Basic form. Here's a warning : Convert to lambda
        JavaInterface.setNoParam(object : INoParam {
            override fun foo() {
                doSth()
            }
        })

        // 2. Lambda without param. Convert from 1.
        JavaInterface.setNoParam { doSth() }

        // 3. Lambda with param
        JavaInterface.setParam { a -> doSth(a) }

        // 4. Lambda with param but not use
        JavaInterface.setParam { _ -> doSth() }

        // 5. Lambda with param but not use.
        JavaInterface.setParam { doSth() }

        // 6. Return value doesn't matter
        JavaInterface.setHasReturn { a -> doSth(a) }

        // 7. Multi params
        JavaInterface.setMultiParam { a, b -> doSth(a) }

        // 8. Multi params, but some of them not use
        JavaInterface.setMultiParam { a, _ -> doSth(a) }

        // 9. Multi methods, can not write into lambda
        JavaInterface.setMultiMethods(object : IMultiMethods {
            override fun foo(a: Int) {
                doSth(a)
            }

            override fun bar(b: String?) {
                doSth()
            }
        })

        // Kotlin set method with java interface, can convert to lambda, but not same as java interface
        kotlinSetWithParam(object : IParam {
            override fun foo(a: Int) {
                doSth(a)
            }
        })

        kotlinSetWithParam(IParam { a -> doSth(a) })

        // Kotlin set method with kotlin interface, can't convert to lambda
        kotlinSetForKotlinInterface(object : IAnKotlinInterfaceParam {
            override fun foo(a: Int) {
                doSth(a)
            }
        })
    }

    fun doSth() = 0
    fun doSth(vararg args: Int) = 1

    fun kotlinSetWithParam(a: JavaInterface.IParam) = 2
    fun kotlinSetForKotlinInterface(a: IAnKotlinInterfaceParam) = 3

}

interface IAnKotlinInterfaceParam {
    fun foo(a: Int)
}
