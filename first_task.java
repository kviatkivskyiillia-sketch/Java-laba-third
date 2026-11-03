import java.util.Scanner;

// ЗАВДАННЯ 1
// Ієрархія: Випробування (Trial) -> Тест (Test), Іспит (Exam) -> Випускний іспит (FinalExam)
public class first_task {

    static class Trial {
        protected String name;
        protected String date;
        protected int maxScore;

        Trial(String name, String date, int maxScore) {
            this.name = name;
            this.date = date;
            this.maxScore = maxScore;
        }

        void Show() {
            System.out.println("Випробування: " + name + ", дата: " + date + ", макс. бал: " + maxScore);
        }
    }

    static class Test extends Trial {
        private int questions;
        private int minutes;

        Test(String name, String date, int maxScore, int questions, int minutes) {
            super(name, date, maxScore);
            this.questions = questions;
            this.minutes = minutes;
        }

        @Override
        void Show() {
            System.out.println("Тест: " + name + ", дата: " + date + ", макс. бал: " + maxScore
                    + ", питань: " + questions + ", час: " + minutes + " хв");
        }
    }

    static class Exam extends Trial {
        protected String subject;
        protected String examiner;

        Exam(String name, String date, int maxScore, String subject, String examiner) {
            super(name, date, maxScore);
            this.subject = subject;
            this.examiner = examiner;
        }

        @Override
        void Show() {
            System.out.println("Іспит: " + name + ", дата: " + date + ", макс. бал: " + maxScore
                    + ", предмет: " + subject + ", екзаменатор: " + examiner);
        }
    }

    static class FinalExam extends Exam {
        private String specialty;
        private int commission;

        FinalExam(String name, String date, int maxScore, String subject, String examiner,
                  String specialty, int commission) {
            super(name, date, maxScore, subject, examiner);
            this.specialty = specialty;
            this.commission = commission;
        }

        @Override
        void Show() {
            System.out.println("Випускний іспит: " + name + ", дата: " + date + ", макс. бал: " + maxScore
                    + ", предмет: " + subject + ", екзаменатор: " + examiner
                    + ", спеціальність: " + specialty + ", членів комісії: " + commission);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Скільки об'єктів ввести? ");
        int n = in.nextInt();
        in.nextLine();
        Trial[] arr = new Trial[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Об'єкт " + (i + 1) + ". Тип (1-випробування, 2-тест, 3-іспит, 4-випускний іспит): ");
            int type = in.nextInt();
            in.nextLine();
            System.out.print("Назва: ");
            String name = in.nextLine();
            System.out.print("Дата: ");
            String date = in.nextLine();
            System.out.print("Максимальний бал: ");
            int max = in.nextInt();
            in.nextLine();
            if (type == 2) {
                System.out.print("Кількість питань: ");
                int q = in.nextInt();
                System.out.print("Час (хв): ");
                int t = in.nextInt();
                in.nextLine();
                arr[i] = new Test(name, date, max, q, t);
            } else if (type == 3 || type == 4) {
                System.out.print("Предмет: ");
                String subj = in.nextLine();
                System.out.print("Екзаменатор: ");
                String ex = in.nextLine();
                if (type == 3) {
                    arr[i] = new Exam(name, date, max, subj, ex);
                } else {
                    System.out.print("Спеціальність: ");
                    String spec = in.nextLine();
                    System.out.print("Членів комісії: ");
                    int c = in.nextInt();
                    in.nextLine();
                    arr[i] = new FinalExam(name, date, max, subj, ex, spec, c);
                }
            } else {
                arr[i] = new Trial(name, date, max);
            }
        }
        System.out.println("\nВведений масив:");
        for (Trial t : arr) {
            t.Show();
        }
        in.close();
    }
}