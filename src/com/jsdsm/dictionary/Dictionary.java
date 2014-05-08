package com.jsdsm.dictionary;

import java.util.List;

public interface Dictionary {
    public int getMaxLength();
    public boolean contains(String item, int start, int length);
    public boolean contains(String item);
    public void addAll(List<String> items);
    public void add(String item);
    public void clear();
}
