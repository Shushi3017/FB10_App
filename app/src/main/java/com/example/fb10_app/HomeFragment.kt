package com.example.fb10_app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val prefs = requireActivity().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val layout = view.findViewById<FrameLayout>(R.id.homelayout)
        val title = view.findViewById<TextView>(R.id.textView6)
        if (prefs.getBoolean("dark_mode", false)) {

            layout.setBackgroundColor(Color.parseColor("#0D1B2A"))
            title.setTextColor(Color.WHITE)

        } else {

            layout.setBackgroundResource(R.drawable.registration_bg)
            title.setTextColor(Color.BLACK)

        }
        return view
    }
}