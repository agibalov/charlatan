package me.loki2302.charlatan;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.apache.commons.lang3.text.WordUtils;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.google.common.base.Joiner;

public class Charlatan {
    private final List<String> firstNames = readCsvResource("/first_names.csv");
    private final List<String> lastNames = readCsvResource("/last_names.csv");
    private final List<String> lorem = readCsvResource("/lorem.csv");
    private final List<String> adjectives = readCsvResource("/adjectives.csv");
    private final List<String> nouns = readCsvResource("/nouns.csv");
    private final List<String> tlds = readCsvResource("/tlds.csv");
    
    private final Random random = new Random();
    
    public String firstName() {
        return randomElement(firstNames);
    }
    
    public String lastName() {
        return randomElement(lastNames);
    }
    
    public String userName() {
        String firstName = firstName();
        String lastName = lastName();
        int suffix = random.nextInt(1000);
        String userName = String.format("%s%s%d", firstName.substring(0, 1), lastName, suffix).toLowerCase();
        return userName;
    }
    
    public String word() {
        return randomElement(lorem);
    }
    
    public String adjective() {
        return randomElement(adjectives);
    }
    
    public String noun() {
        return randomElement(nouns);
    }
    
    public String applicationName() {
        String adjective = WordUtils.capitalize(adjective());
        String noun = WordUtils.capitalize(noun());
        return String.format("%s %s", adjective, noun);
    }
    
    public List<String> words(int numberOfWords) {
        return randomElements(lorem, numberOfWords);
    }
           
    public String sentence(int numberOfWords) {
        List<String> words = words(numberOfWords);
        String joinedWords = Joiner.on(' ').join(words);
        joinedWords = String.format("%s.", joinedWords);
        return WordUtils.capitalize(joinedWords, new char[] { '.' });
    }
    
    public List<String> sentences(int numberOfSentences) {
        List<String> sentences = new ArrayList<String>();
        for(int i = 0; i < numberOfSentences; ++i) {
            String sentence = sentence(10);
            sentences.add(sentence);
        }
        return sentences;
    }
    
    public String paragraph(int numberOfSentences) {
        List<String> sentences = sentences(numberOfSentences);
        return Joiner.on(' ').join(sentences);
    }
    
    public List<String> paragraphs(int numberOfParagraphs) {
        List<String> paragraphs = new ArrayList<String>();
        for(int i = 0; i < numberOfParagraphs; ++i) {
            String paragraph = paragraph(5);
            paragraphs.add(paragraph);
        }
        return paragraphs;
    }
    
    public String text(int numberOfParagraphs) {
        List<String> paragraphs = paragraphs(numberOfParagraphs);
        return Joiner.on("\n\n").join(paragraphs);
    }
    
    public void randomImageToStream(String seed, int width, int height, OutputStream outputStream) {
        QRCode
            .from(seed)
            .withSize(width, height)
            .to(ImageType.PNG)
            .writeTo(outputStream);
    }
    
    public String tld() {
        return randomElement(tlds);
    }
    
    private <T> T randomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
    
    private <T> List<T> randomElements(List<T> list, int numberOfElements) {
        List<T> items = new ArrayList<T>();
        for(int i = 0; i < numberOfElements; ++i) {
            items.add(randomElement(list));
        }
        return items;
    }
    
    private static List<String> readCsvResource(String resourceName) {
        InputStream is = Charlatan.class.getResourceAsStream(resourceName);
        try {
            InputStreamReader isr = new InputStreamReader(is);
            try {
                ICsvListReader csvReader = new CsvListReader(isr, CsvPreference.STANDARD_PREFERENCE);
                try {
                    return csvReader.read();
                } catch (IOException e) {
                    return null;
                } finally {
                    try {
                        csvReader.close();
                    } catch (IOException e) {}
                }
            } finally {
                try {
                    isr.close();
                } catch (IOException e) {}
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {}
        }
    }
}