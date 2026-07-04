package com.example.fb10_app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val prefs = requireActivity().getSharedPreferences(
            "ThemePrefs",
            Context.MODE_PRIVATE
        )

        val layout = view.findViewById<FrameLayout>(R.id.aboutlayout)
        val text = view.findViewById<TextView>(R.id.textView85)

        if (prefs.getBoolean("dark_mode", false)) {
            layout.setBackgroundColor(Color.parseColor("#0D1B2A"))
            text.setTextColor(Color.WHITE)
        } else {
            layout.setBackgroundResource(R.drawable.registration_bg)
            text.setTextColor(Color.BLACK)
        }

        return view
    }
}