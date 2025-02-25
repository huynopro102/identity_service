package identity.TuanHuy.functional_interface;

// 1 . functional interface just one method abstract
// 2 . there are to two ways implements a functional interface
// method 1 : before lambda expressions existed , must a class to implements interface
// method 2 : after lambda expressions were introduced

@FunctionalInterface
public interface animal {
    void eat();
}
