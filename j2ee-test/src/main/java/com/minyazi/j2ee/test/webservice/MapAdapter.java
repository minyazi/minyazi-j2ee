package com.minyazi.j2ee.test.webservice;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapConvertor, Map<String, User>> {
    @Override
    public MapConvertor marshal(Map<String, User> entries) throws Exception {
        MapConvertor result = new MapConvertor();
        for (Map.Entry<String, User> entry : entries.entrySet()) {
            result.addEntry(new MapConvertor.MapEntry(entry));
        }
        return result;
    }
    
    @Override
    public Map<String, User> unmarshal(MapConvertor entries) throws Exception {
        Map<String, User> result = new LinkedHashMap<String, User>();
        for (MapConvertor.MapEntry entry : entries.getEntries()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
