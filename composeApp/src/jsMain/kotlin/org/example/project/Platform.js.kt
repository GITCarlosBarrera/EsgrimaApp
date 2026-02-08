package org.example.project

class JsPlatform: Platform
actual fun getPlatform(): Platform = JsPlatform()