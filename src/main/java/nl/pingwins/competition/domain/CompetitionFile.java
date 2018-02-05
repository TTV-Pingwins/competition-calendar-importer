package nl.pingwins.competition.domain;

import java.io.Serializable;
import java.util.UUID;

public class CompetitionFile implements Serializable {

  private UUID id;
  private String name;
  private byte[] content;

  public CompetitionFile(String name, byte[] content) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.content = content;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public byte[] getContent() {
    return content;
  }

}
