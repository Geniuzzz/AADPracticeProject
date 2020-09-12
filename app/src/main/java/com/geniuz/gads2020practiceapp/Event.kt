package com.geniuz.gads2020practiceapp
class Event<out T>(private val value: T){

    private var isHandled = false
     fun latestValue()  = value

    fun getValueIfFirstTme(): T?{
        return if (isHandled) null else {

            isHandled = true
            value
        }
    }



}