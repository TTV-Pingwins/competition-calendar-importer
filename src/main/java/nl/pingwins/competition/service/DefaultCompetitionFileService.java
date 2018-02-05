package nl.pingwins.competition.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import nl.pingwins.competition.ApplicationProperties;
import nl.pingwins.competition.domain.CompetitionFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
public class DefaultCompetitionFileService implements CompetitionFileService {

  private File baseDir;
  private String prefix;
  private String suffix;
  
  DefaultCompetitionFileService(@Autowired ApplicationProperties applicationProperties) {
    baseDir = applicationProperties.getFileUploadBaseDir();
    prefix = applicationProperties.getFileUploadPrefix();
    suffix = applicationProperties.getFileUploadSuffix();
  }
  
  @Override
  public List<CompetitionFile> listFiles() {
    File[] files = baseDir.listFiles((dir, name) -> name.startsWith(prefix) && name.endsWith(suffix));
    List<CompetitionFile> competitionFiles = new ArrayList<>();
    if (files != null) {
      for (File file : files) {
        try {
          CompetitionFile competitionFile = (CompetitionFile) SerializationUtils.deserialize(Files.readAllBytes(file.toPath()));
          competitionFiles.add(competitionFile);
        } catch (IOException e) {
          //TODO:
          e.printStackTrace();
        }
      }
    }
    return competitionFiles;
  }

  @Override
  public void save(String name, byte[] fileContent) {
    Set<PosixFilePermission> filePermissions = new HashSet<>();
    filePermissions.add(PosixFilePermission.OWNER_READ);
    filePermissions.add(PosixFilePermission.OWNER_WRITE);
    try {
      Path filePath = Files.createTempFile(baseDir.toPath(), prefix, suffix, PosixFilePermissions.asFileAttribute(filePermissions));
      Files.write(filePath,
              SerializationUtils.serialize(new CompetitionFile(name, fileContent)),
              StandardOpenOption.CREATE);
    } catch (IOException e) {
      //TODO:
      e.printStackTrace();
    }
  }

  @Override
  public CompetitionFile findFileById(UUID id) {
    //TODO:
    return null;
  }
}
