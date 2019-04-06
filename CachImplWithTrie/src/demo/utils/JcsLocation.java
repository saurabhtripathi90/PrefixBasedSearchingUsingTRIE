package demo.utils;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

public class JcsLocation {

	private CacheAccess<String, PrefixTrie> cache = null;

	public JcsLocation() {
		try {
			System.out.println("Enter Try");
			cache = JCS.getInstance("default");
			System.out.println("Exit try");
		} catch (CacheException e) {
			System.out.println("Problem initializing cache");
		} catch (Exception e) {
			System.out.println("Final Cache");
		}
	}

	public void putInCache(PrefixTrie pt) {
		String key = "TrieCache";
		try {
			cache.put(key, pt);
		} catch (CacheException e) {
			System.out.println("Problem putting trie in cache");
		}
	}

	public PrefixTrie retrieveFromCache(String ptKey) {
		return cache.get(ptKey);
	}
}
