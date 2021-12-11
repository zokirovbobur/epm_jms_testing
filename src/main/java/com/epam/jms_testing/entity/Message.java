package com.epam.jms_testing.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
  @Id @GeneratedValue
  private int id;
  private String content;

  public Message(String content) {
    this.content = content;
  }
}
