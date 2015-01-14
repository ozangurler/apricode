package com.apricode.omby.domain;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
 
public class DistributedMap {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        ConcurrentMap<String, String> map = h.getMap("my-distributed-map");
        map.put("key", "value");
        map.get("key");
         
        //Concurrent Map methods
        map.putIfAbsent("somekey", "somevalue");
        map.replace("key", "value", "newvalue");
        
        System.in.read();
        
        map.get("key1");
        System.in.read();
        
    }
}     
