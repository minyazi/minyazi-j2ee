package com.minyazi.test.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="MapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapConvertor {
    private List<MapEntry> entries = new ArrayList<MapEntry>();
    
    public void addEntry(MapEntry entry) {
        entries.add(entry);
    }
    
    public List<MapEntry> getEntries() {
        return entries;
    }
    
    public static class MapEntry {
        private String key;
        private User value;
        
        public MapEntry() {
            super();
        }
        
        public MapEntry(Map.Entry<String, User> entry) {
            super();
            this.key = entry.getKey();
            this.value = entry.getValue();
        }
        
        public MapEntry(String key, User value) {
            super();
            this.key = key;
            this.value = value;
        }
        
        public String getKey() {
            return key;
        }
        
        public void setKey(String key) {
            this.key = key;
        }
        
        public User getValue() {
            return value;
        }
        
        public void setValue(User value) {
            this.value = value;
        }
    }
}
