package org.example.project

class JVMPlatform: Platform

actual fun getPlatform(): Platform = JVMPlatform()