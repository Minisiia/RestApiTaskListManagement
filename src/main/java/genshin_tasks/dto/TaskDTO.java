package genshin_tasks.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TaskDTO {
    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 30 characters")
    private String title;

    @Column(name = "description")
    @NotEmpty(message = "Description should not be empty")
    private String description;

    public String getTitle() {
        return title;
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
}
