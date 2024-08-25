package dance.brain.scbtspring.database.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return Optional.ofNullable(ExpressionUtils.allOf(predicates))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }
}
