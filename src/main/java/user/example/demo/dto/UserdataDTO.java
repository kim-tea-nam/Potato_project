package user.example.demo.dto;

import lombok.Data;

@Data
public class UserdataDTO {

  private Long id;
  private String title;
  private String content;

  public UserdataDTO(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

}
