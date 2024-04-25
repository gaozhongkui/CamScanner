package com.gaozhongkui.camscanner

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat

class MainActivity : ComponentActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        if (OpenCVLoader.initLocal()) {
            Log.d(TAG, "onCreate() 加载成功")
        } else {
            Log.d(TAG, "onCreate() 加载失败")
        }

        val mOpenCvCameraView = findViewById(R.id.tutorial1_activity_java_surface_view) as? CameraBridgeViewBase
        mOpenCvCameraView?.apply {
            setCameraPermissionGranted()
            setCvCameraViewListener(this@MainActivity)
            enableView()
        }

        Log.d(TAG, "onCreate() called with: $mOpenCvCameraView")

        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), 100)
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        Log.d(TAG, "onCameraViewStarted() called with: width = $width, height = $height")
    }

    override fun onCameraViewStopped() {
        Log.d(TAG, "onCameraViewStopped() called")
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
        return inputFrame.rgba()
    }


    companion object {
        private const val TAG = "MainActivity"
    }

}
