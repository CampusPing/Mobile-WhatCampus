package core.data.cache

abstract class Cache<K, V> {
    private val cache = mutableMapOf<K, V>()

    protected abstract val emptyValue: V

    operator fun contains(key: K): Boolean = cache.containsKey(key)

    operator fun get(key: K): V = cache[key] ?: emptyValue

    operator fun set(key: K, value: V) {
        cache[key] = value
    }

    fun clear() {
        cache.clear()
    }

    fun remove(key: K) {
        cache.remove(key)
    }

    fun keys(): Set<K> = cache.keys
}
