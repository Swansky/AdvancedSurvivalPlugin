package fr.swansky.advancedsurvivalplugin.data;

public interface Manager<T> {
    void save();
    void add(T t) throws Exception;
    void delete(T t);
    void load();
}
