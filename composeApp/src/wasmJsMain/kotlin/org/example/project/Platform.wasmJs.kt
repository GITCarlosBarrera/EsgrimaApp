package org.example.project

class WasmPlatform: Platform

actual fun getPlatform(): Platform = WasmPlatform()