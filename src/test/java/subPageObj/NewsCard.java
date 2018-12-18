package subPageObj;

import java.util.Objects;

public class NewsCard {
    private String date;
    private String detailDate;
    private String title;
    private String detailTitle;
    private String description;
    private String text;
    private String source;

    public NewsCard(String date, String title, String description, String text, String source) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.text = text;
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsCard)) return false;
        NewsCard newsCard = (NewsCard) o;
        return Objects.equals(getDate(), newsCard.getDate()) &&
                Objects.equals(getTitle(), newsCard.getTitle()) &&
                Objects.equals(getDescription(), newsCard.getDescription()) &&
                Objects.equals(getText(), newsCard.getText()) &&
                Objects.equals(getSource(), newsCard.getSource());
    }

}
