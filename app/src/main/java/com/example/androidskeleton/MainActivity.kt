package com.example.androidskeleton

import android.graphics.Color
import android.graphics.Color.*
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eudycontreras.boneslibrary.framework.skeletons.SkeletonDrawable
import com.example.androidskeleton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isSkeletonDraw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge support
        enableEdgeToEdge()

        // Set up data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up WindowInsets handling for padding adjustment
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up SkeletonDrawable functionality
        binding.showSkeleton.setOnClickListener {
            if(!isSkeletonDraw){
                val viewGroup = binding.main
                SkeletonDrawable.create(viewGroup).apply {
                    this.getProps().apply {
                        enabled = true
                        allowSavedState = true
                        shimmerRayProperties.apply {
                            shimmerRayThickness = 50f
                            shimmerRayTilt = 0.3f
                            shimmerRayCount = 5
                            shimmerRaySpeedMultiplier = 1f
                            shimmerRayAnimDuration = 1000
                            skeletonBackgroundColor = null
                            allowBoneGeneration = true
                        }
                    }
                }
                isSkeletonDraw = true
                binding.showSkeleton.text= "hide skeleton"
            }else{
                val viewGroup = binding.main
                SkeletonDrawable.create(viewGroup).apply {
                    this.getProps().apply {
                        enabled = false
                        allowSavedState = true
                    }
                }
                isSkeletonDraw = false
                binding.showSkeleton.text= "show skeleton"
            }

        }
    }
}
