package com.example.allcomponentvalidationjetpack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform