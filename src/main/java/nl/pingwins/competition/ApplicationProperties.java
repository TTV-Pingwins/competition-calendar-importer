package nl.pingwins.competition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pingwins")
public class ApplicationProperties {

  private File fileUploadBaseDir;
  private String fileUploadSuffix;
  private String fileUploadPrefix;

  public File getFileUploadBaseDir() {
    return fileUploadBaseDir;
  }

  public String getFileUploadPrefix() {
    return fileUploadPrefix;
  }

  public String getFileUploadSuffix() {
    return fileUploadSuffix;
  }

  public void setFileUploadBaseDir(String baseDirString) {
    File baseDir = new File(baseDirString);
    if (!baseDir.exists()) {
      try {
        Files.createDirectory(baseDir.toPath());
      } catch (IOException e) {
        throw new IllegalStateException("configuration 'baseDir' is not a directory.");
      }
    }
    this.fileUploadBaseDir = baseDir;
  }

  public void setFileUploadPrefix(String fileUploadPrefix) {
    this.fileUploadPrefix = fileUploadPrefix;
  }

  public void setFileUploadSuffix(String fileUploadSuffix) {
    this.fileUploadSuffix = fileUploadSuffix;
  }
}
