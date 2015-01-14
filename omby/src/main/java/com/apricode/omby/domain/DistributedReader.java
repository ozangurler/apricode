package com.apricode.omby.domain;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class DistributedReader {
	
	    public static void main(String[] args) throws IOException {
	        Config config = new Config();
	        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
	        ConcurrentMap<String, String> map = h.getMap("my-distributed-map");
//	        map.put("key", "value");
//	        map.get("key");
	         
	        //Concurrent Map methods
	        System.out.println(map.get("somekey"));
	        map.put("key1", "gluglu");
	        
	        System.in.read();
	        
//	        System.in.read();
	    }
	    
}
