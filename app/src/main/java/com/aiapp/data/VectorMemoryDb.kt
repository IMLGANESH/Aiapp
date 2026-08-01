package com.aiapp.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.HnswIndex

@Entity
data class MemoryContext(
    @Id var id: Long = 0,
    var textContent: String = "",
    
    // Stores the AI embedding (e.g., 1536 dimensions for OpenAI/Gemini models)
    @HnswIndex(dimensions = 1536)
    var embeddingVector: FloatArray? = null
)

class VectorMemoryDb(private val boxStore: BoxStore) {
    private val memoryBox = boxStore.boxFor(MemoryContext::class.java)

    fun saveInteraction(text: String, vector: FloatArray) {
        memoryBox.put(MemoryContext(textContent = text, embeddingVector = vector))
    }

    fun retrieveRelevantContext(queryVector: FloatArray, topK: Int = 3): List<String> {
        // Performs a local similarity search on the device
        val query = memoryBox.query()
            .nearestNeighbors(MemoryContext_.embeddingVector, queryVector, topK)
            .build()
        return query.find().map { it.textContent }
    }
}
