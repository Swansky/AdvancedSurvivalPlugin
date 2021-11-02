package fr.swansky.advancedsurvivalplugin.data;

public interface Controller<T> {
    void save(T t);
    void add(T t);
    void delete(T t);
    void load();
}
