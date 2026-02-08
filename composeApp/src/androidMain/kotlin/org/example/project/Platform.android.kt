package org.example.project

import android.os.Build

class AndroidPlatform : Platform

actual fun getPlatform(): Platform = AndroidPlatform()