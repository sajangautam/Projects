package emporium;

import java.util.*;

public class MultiMap<K, V>
{
	/*
	private HashSet<String> V = new HashSet<>();
	private String K; 
	private HashMap<K, V[]> map = new HashMap<>();
	*/
	//private K key;
	
	private HashMap<K, HashSet<V>> map = new HashMap<>();
	
	public void put(K key, V value)
	{
		if(map.get(key) == null)
		{
			HashSet<V> values = new HashSet<>();
			values.add(value);
			map.put(key, values);
		}
		else
		{
			HashSet<V> values;
			values = map.get(key);
			values.add(value);
			map.put(key, values);
		}
	}
	
	public Object[] get(K key)
	{
		if(map.get(key) == null)
		{
			ArrayList<Object> objects = new ArrayList<>();
			return objects.toArray();
		}
		
		return map.get(key).toArray();
	}
	
}
