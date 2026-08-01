package com.aiapp.network

class ApiKeyRotator(private val apiKeys: List<String>) {
    private var currentIndex = 0
    private var usageCount = 0
    private val limitPerKey = 45 // Switch just before typical free-tier limits

    @Synchronized
    fun getActiveKey(): String {
        if (apiKeys.isEmpty()) throw IllegalStateException("No API keys configured")
        
        usageCount++
        if (usageCount >= limitPerKey) {
            currentIndex = (currentIndex + 1) % apiKeys.size
            usageCount = 0 // Reset for the next key
        }
        return apiKeys[currentIndex]
    }

    @Synchronized
    fun reportKeyExhausted() {
        // Immediately rotate if a 429 Too Many Requests error occurs
        currentIndex = (currentIndex + 1) % apiKeys.size
        usageCount = 0
    }
}
