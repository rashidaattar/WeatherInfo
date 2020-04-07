package com.demo.weatherinfo

import com.google.gson.Gson
import java.io.File
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass


/**
 * Created by Rashida on 4/5/20.
 *
 */

abstract class JUnitTestHelper {

    /**
     * these two methods is to make sure everyone is following the same structure of writing unit tests
     */
    abstract fun before()

    abstract fun after()
    /**
     * This will read the file and it will convert the content to a String object
     */
    protected fun getJson(path: String): String {
        val resource = this.javaClass.classLoader?.getResource(path)
        assert(resource != null)
        val file = File(resource?.path)
        return String(file.readBytes())
    }

    /**
     * This function will take the json string as an input and it will convert it to the Class you wanted
     * using Gson parser
     */
    private fun <T> jsonToObject(json: String, classType: Class<T>): T {
        return Gson().fromJson(json, classType)
    }

    /**
     * this function is what we will use in our test cases when we want to read some ready json files
     * to test the behavior of our viewModels
     */
    fun <T> getObjectFromJson(path: String, classType: Class<T>): T = jsonToObject(getJson(path), classType)

    fun <T : Any> getTypedListFromJson(gson: Gson, path: String, kClass: KClass<T>): MutableList<T> = gson.fromJson<MutableList<T>>(getJson(path), ListOfSomething(kClass.java))

    internal class ListOfSomething<X>(wrapped: Class<X>) : ParameterizedType {

        private val wrapped: Class<*>

        init {
            this.wrapped = wrapped
        }

        override fun getActualTypeArguments(): Array<Type> {
            return arrayOf(wrapped)
        }

        override fun getRawType(): Type {
            return ArrayList::class.java
        }

        override fun getOwnerType(): Type? {
            return null
        }
    }
}