// ЗАВДАННЯ 2
// Абстрактний клас Triad (трійка чисел) та похідні Date і Time
public class second_task {

    static abstract class Triad {
        protected int a, b, c;

        Triad(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        abstract void increment();

        abstract void print();
    }

    static class Date extends Triad {
        Date(int day, int month, int year) {
            super(day, month, year);
        }

        int daysInMonth() {
            if (b == 2) {
                boolean leap = (c % 4 == 0 && c % 100 != 0) || c % 400 == 0;
                return leap ? 29 : 28;
            }
            if (b == 4 || b == 6 || b == 9 || b == 11) return 30;
            return 31;
        }

        @Override
        void increment() {
            a++;
            if (a > daysInMonth()) {
                a = 1;
                b++;
                if (b > 12) {
                    b = 1;
                    c++;
                }
            }
        }

        @Override
        void print() {
            System.out.println("Дата: " + this);
        }

        @Override
        public String toString() {
            return String.format("%02d.%02d.%04d", a, b, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Date)) return false;
            Date d = (Date) o;
            return a == d.a && b == d.b && c == d.c;
        }
    }

    static class Time extends Triad {
        Time(int h, int m, int s) {
            super(h, m, s);
        }

        @Override
        void increment() {
            c++;
            if (c == 60) { c = 0; b++; }
            if (b == 60) { b = 0; a++; }
            if (a == 24) { a = 0; }
        }

        @Override
        void print() {
            System.out.println("Час: " + this);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d:%02d", a, b, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Time)) return false;
            Time t = (Time) o;
            return a == t.a && b == t.b && c == t.c;
        }
    }

    public static void main(String[] args) {
        Triad[] arr = {
                new Date(28, 2, 2024),
                new Date(31, 12, 2025),
                new Time(23, 59, 59),
                new Time(10, 15, 30)
        };
        System.out.println("Початкові значення:");
        for (Triad t : arr) t.print();
        System.out.println("\nПісля increment():");
        for (Triad t : arr) {
            t.increment();
            t.print();
        }
        System.out.println("\nПеревірка toString(): " + arr[0].toString() + " та " + arr[2].toString());
        System.out.println("Перевірка equals():");
        System.out.println("29.02.2024 equals 29.02.2024 -> " + arr[0].equals(new Date(29, 2, 2024)));
        System.out.println("01.01.2026 equals 02.01.2026 -> " + arr[1].equals(new Date(2, 1, 2026)));
        System.out.println("00:00:00 equals 00:00:00 -> " + arr[2].equals(new Time(0, 0, 0)));
        System.out.println("Дата equals Час -> " + arr[0].equals(arr[3]));
    }
}