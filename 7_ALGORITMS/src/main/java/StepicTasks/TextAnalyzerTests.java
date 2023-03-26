package StepicTasks;

import java.util.Arrays;

enum Label {
    SPAM,
    NEGATIVE_TEXT,
    TOO_LONG,
    OK
}

interface TextAnalyzer {
    public Label processText(String text);
}

abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    public Label processText(String text) {
        return Label.OK;
    }
}

class SpamAnalyzer extends KeywordAnalyzer {
    private final String keywords[];

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public Label processText(String text) {
        for (String word: keywords)
            if (true == text.contains(word))
                return Label.SPAM;
        return Label.OK;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}

class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String keywords[] = new String[] {":(", "=(", ":|"};

    @Override
    public Label processText(String text) {
        for (String word: keywords)
            if (true == text.contains(word))
                return Label.NEGATIVE_TEXT;
        return Label.OK;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}

class TooLongTextAnalyzer implements TextAnalyzer {
    private final int maxLength;

    public TooLongTextAnalyzer(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Label processText(String text) {
        if (text.length() >= this.maxLength)
            return Label.TOO_LONG;
        return Label.OK;
    }
}

public class TextAnalyzerTests
{
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer: analyzers) {
            final Label result = analyzer.processText(text);
            if (Label.OK != result)
                return result;
        }
        return Label.OK;
    }

    public static void main(String[] args)
    {

        TextAnalyzer spam = new SpamAnalyzer(new String[] { "One", "Two", "Three" });
        System.out.println(spam.processText("TwoTwo"));

        TextAnalyzer lengthAnalyzer = new TooLongTextAnalyzer(32);
        System.out.println(lengthAnalyzer.processText("Two22222222222222222222222222222222Two"));

        TextAnalyzer negativeAnalyzer = new NegativeTextAnalyzer();
        System.out.println(negativeAnalyzer.processText("Hello :(("));
    }
}
