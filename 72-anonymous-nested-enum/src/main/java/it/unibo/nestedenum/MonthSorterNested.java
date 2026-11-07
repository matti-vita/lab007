package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.List;

import it.unibo.functional.api.Function;
import it.unibo.functional.Transformers;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {
        JANUARY("january", 31),
        FEBRUARY("february", 28),
        MARCH("march", 31),
        APRIL("april", 30),
        MAY("may", 31),
        JUNE("june", 30),
        JULY("july", 31),
        AUGUST("august", 31),
        SEPTEMBER("september", 30),
        OCTOBER("october", 31),
        NOVEMBER("november", 30),
        DECEMBER("december", 31);

        private final String name;
        private final int days;

        private Month(final String name, final int days) {
            this.name = name;
            this.days = days;
        }

        static Month fromString(final String input) {
            if (input == null) {
                throw new IllegalArgumentException("The text cannot be null");
            }
            final String lowerInput = input.toLowerCase();
            List<Month> monthList = List.of(Month.values());
            List<Month> matches = Transformers.select(monthList, new Function<Month, Boolean>() {
                @Override
                public Boolean call(final Month currentMonth) {
                    return currentMonth.name.startsWith(lowerInput);
                }
            });
            if (matches.size() == 1) {
                return matches.get(0);
            }
            else {
                throw new IllegalArgumentException("Invalid or ambiguous string: " + input);
            }
        }
    }

    private static final class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
    }

    private static final class SortByDate implements Comparator<String> {
        @Override
        public int compare(final String s1, final String s2) {
            final var m1 = Month.fromString(s1);
            final var m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
