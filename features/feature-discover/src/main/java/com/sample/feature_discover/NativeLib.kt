package com.sample.feature_discover

class NativeLib {

    /**
     * A native method that is implemented by the 'feature_discover' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'feature_discover' library on application startup.
        init {
            System.loadLibrary("feature_discover")
        }
    }
}