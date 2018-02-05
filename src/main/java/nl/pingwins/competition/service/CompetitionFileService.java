package nl.pingwins.competition.service;

import java.util.List;
import java.util.UUID;

import nl.pingwins.competition.domain.CompetitionFile;

public interface CompetitionFileService {

  List<CompetitionFile> listFiles();

  void save(String name, byte[] file);

  CompetitionFile findFileById(UUID id);
}
