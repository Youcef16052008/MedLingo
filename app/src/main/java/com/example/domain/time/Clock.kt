package com.example.domain.time

interface Clock {
    fun now(): Long
}

object SystemClock : Clock {
    override fun now(): Long = System.currentTimeMillis()
}

class FakeClock(var currentTime: Long) : Clock {
    override fun now(): Long = currentTime
    fun advance(durationMillis: Long) { currentTime += durationMillis }
}