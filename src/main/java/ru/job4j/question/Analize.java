package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> items;
        items = current.
                stream().
                collect(Collectors.toMap(
                e -> e.getId(),
                e -> e));
        int added = items.size();
        int changed = 0;
        int deleted = 0;
        for (var i : previous) {
            var item = items.get(i.getId());
            if (item != null && !(item.getName().equals(i.getName()))) {
                changed++;
            }
            if (item == null) {
                deleted++;
            }
            if (item != null && item.getId() == i.getId()) {
                added--;
            }
        }
        return new Info(added, changed, deleted);
    }
}
