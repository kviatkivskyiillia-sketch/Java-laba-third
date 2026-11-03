// ЗАВДАННЯ 3
// Те саме, але замість абстрактного класу - інтерфейс
public class third_task {

    interface ITriad {
        void increment();

        void print();
    }

    static class DateI implements ITriad {
        private int day, month, year;

        DateI(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        private int daysInMonth() {
            if (month == 2)
                return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? 29 : 28;
            if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
            return 31;
        }

        @Override
        public void increment() {
            day++;
            if (day > daysInMonth()) { day = 1; month++; }
            if (month > 12) { month = 1; year++; }
        }

        @Override
        public void print() {
            System.out.println("Дата: " + this);
        }

        @Override
        public String toString() {
            return String.format("%02d.%02d.%04d", day, month, year);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DateI)) return false;
            DateI d = (DateI) o;
            return day == d.day && month == d.month && year == d.year;
        }
    }

    static class TimeI implements ITriad {
        private int h, m, s;

        TimeI(int h, int m, int s) {
            this.h = h;
            this.m = m;
            this.s = s;
        }

        @Override
        public void increment() {
            s++;
            if (s == 60) { s = 0; m++; }
            if (m == 60) { m = 0; h++; }
            if (h == 24) h = 0;
        }

        @Override
        public void print() {
            System.out.println("Час: " + this);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d:%02d", h, m, s);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TimeI)) return false;
            TimeI t = (TimeI) o;
            return h == t.h && m == t.m && s == t.s;
        }
    }

    public static void main(String[] args) {
        ITriad[] arr = {
                new DateI(28, 2, 2023),
                new DateI(30, 4, 2026),
                new TimeI(12, 59, 59),
                new TimeI(23, 59, 59)
        };
        System.out.println("Початкові значення:");
        for (ITriad t : arr) t.print();
        System.out.println("\nПісля increment():");
        for (ITriad t : arr) {
            t.increment();
            t.print();
        }
        System.out.println("\nПеревірка toString(): " + arr[1].toString() + " та " + arr[3].toString());
        System.out.println("Перевірка equals():");
        System.out.println("01.03.2023 equals 01.03.2023 -> " + arr[0].equals(new DateI(1, 3, 2023)));
        System.out.println("13:00:00 equals 13:00:01 -> " + arr[2].equals(new TimeI(13, 0, 1)));
        System.out.println("Дата equals Час -> " + arr[1].equals(arr[2]));
    }
}