package dance.brain.scbtspring.database.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QPredicate {

    private QPredicate() {
    }

    public static QPredicate builder() {
        return new QPredicate();
    }

    private List<Predicate> predicates = new ArrayList<>();

    public <T> QPredicate add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Predicate build() {
        return ExpressionUtils.allOf(predicates);
    }
}
