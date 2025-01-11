// LabWork6.java

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Узагальнений клас для музичних композицій.
 */
abstract class MusicalComposition {
    private String title;
    private double duration; // Тривалість у хвилинах
    private String genre;
    private double price;

    public MusicalComposition(String title, double duration, String genre, double price) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() { return title; }
    public double getDuration() { return duration; }
    public String getGenre() { return genre; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("Назва: %s, Тривалість: %.2f хв, Жанр: %s, Ціна: %.2f грн",
                             title, duration, genre, price);
    }
}

/**
 * Клас для поп-музики.
 */
class PopSong extends MusicalComposition {
    private String artist;

    public PopSong(String title, double duration, double price, String artist) {
        super(title, duration, "Поп", price);
        this.artist = artist;
    }

    public String getArtist() { return artist; }

    @Override
    public String toString() {
        return String.format("Поп-пісня -> %s, Виконавець: %s", super.toString(), artist);
    }
}

/**
 * Клас для рок-музики.
 */
class RockSong extends MusicalComposition {
    private String band;

    public RockSong(String title, double duration, double price, String band) {
        super(title, duration, "Рок", price);
        this.band = band;
    }

    public String getBand() { return band; }

    @Override
    public String toString() {
        return String.format("Рок-пісня -> %s, Гурт: %s", super.toString(), band);
    }
}

/**
 * Клас для класичної музики.
 */
class ClassicalComposition extends MusicalComposition {
    private String composer;

    public ClassicalComposition(String title, double duration, double price, String composer) {
        super(title, duration, "Класична", price);
        this.composer = composer;
    }

    public String getComposer() { return composer; }

    @Override
    public String toString() {
        return String.format("Класична композиція -> %s, Композитор: %s", super.toString(), composer);
    }
}

/**
 * Клас альбому, який складається з масиву музичних композицій.
 */
class Album {
    private List<MusicalComposition> compositions;
    private double totalPrice;

    public Album() {
        compositions = new ArrayList<>();
        totalPrice = 0.0;
    }

    public void addComposition(MusicalComposition composition) {
        compositions.add(composition);
        totalPrice += composition.getPrice();
    }

    public double calculateTotalDuration() {
        double totalDuration = 0.0;
        for (MusicalComposition composition : compositions) {
            totalDuration += composition.getDuration();
        }
        return totalDuration;
    }

    public void sortByGenre() {
        compositions.sort(Comparator.comparing(MusicalComposition::getGenre));
    }

    public List<MusicalComposition> findCompositionsByDuration(double minDuration, double maxDuration) {
        List<MusicalComposition> result = new ArrayList<>();
        for (MusicalComposition composition : compositions) {
            if (composition.getDuration() >= minDuration && composition.getDuration() <= maxDuration) {
                result.add(composition);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Альбом:\n");
        for (MusicalComposition composition : compositions) {
            sb.append(composition.toString()).append("\n");
        }
        sb.append(String.format("Загальна ціна альбому: %.2f грн\n", totalPrice));
        sb.append(String.format("Загальна тривалість альбому: %.2f хв\n", calculateTotalDuration()));
        return sb.toString();
    }
}

/**
 * Клас із виконавчим методом для лабораторної роботи №6.
 */
public class LabWork6 {
    public static void main(String[] args) {
        int studentNumber = 4;
        int C13 = studentNumber % 13;
        System.out.println("C13: " + C13);

        try {
            if (C13 == 4) {
                Album album = new Album();
                album.addComposition(new PopSong("Shape of You", 4.24, 50.0, "Ed Sheeran"));
                album.addComposition(new RockSong("Bohemian Rhapsody", 5.55, 60.0, "Queen"));
                album.addComposition(new ClassicalComposition("Symphony No.5", 7.30, 80.0, "Beethoven"));
                album.addComposition(new PopSong("Blinding Lights", 3.20, 45.0, "The Weeknd"));
                album.addComposition(new RockSong("Stairway to Heaven", 8.02, 70.0, "Led Zeppelin"));

                System.out.println("Початковий альбом:");
                System.out.println(album);

                album.sortByGenre();
                System.out.println("Альбом після сортування за жанром:");
                System.out.println(album);

                double minDuration = 4.0;
                double maxDuration = 6.0;
                List<MusicalComposition> foundCompositions = album.findCompositionsByDuration(minDuration, maxDuration);
                System.out.printf("Композиції з тривалістю від %.2f до %.2f хв:\n", minDuration, maxDuration);
                for (MusicalComposition composition : foundCompositions) {
                    System.out.println(composition);
                }
            } else {
                throw new UnsupportedOperationException("Невідомий варіант завдання для C13 = " + C13);
            }
        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
