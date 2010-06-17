package com.scenomania.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class DummyMap implements Map {
        public Collection values() {return null;}
        public Object put(Object key, Object value) {return null;}
        public Set keySet() {return null;}
        public boolean isEmpty() {return false;}
        public int size() {return 0;}
        public void putAll(Map t) {}
        public void clear() {}
        public boolean containsValue(Object value) {return false;}
        public Object remove(Object key) {return null;  }
        public boolean containsKey(Object key) {return false;}
        public Set entrySet() {return null;}

        public abstract Object get(Object obj);
}