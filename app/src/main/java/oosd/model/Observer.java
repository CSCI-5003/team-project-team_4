package oosd.model;

public interface Observer {
    public void update(int matchCount, WordGroup correctWords);
}
