package com.aiapp.services

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class AiAccessibilityService : AccessibilityService() {
    
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val rootNode: AccessibilityNodeInfo? = rootInActiveWindow
            val screenText = extractTextFromNode(rootNode)
            
            // Pass this 'screenText' to your AI as context so it knows what app 
            // the user is looking at and what is on their screen.
        }
    }

    private fun extractTextFromNode(node: AccessibilityNodeInfo?): String {
        if (node == null) return ""
        var text = node.text?.toString() ?: ""
        for (i in 0 until node.childCount) {
            text += " " + extractTextFromNode(node.getChild(i))
        }
        return text
    }

    override fun onInterrupt() {
        // Handle service interruptions
    }
}
