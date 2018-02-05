package nl.pingwins.competition.web.controller;

import java.io.IOException;

import nl.pingwins.competition.service.CompetitionFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

  @Autowired
  private CompetitionFileService competitionFileService;

  @RequestMapping("/files")
  public String list(Model model) {
    model.addAttribute("files", competitionFileService.listFiles());
    return "";
  }

  @RequestMapping(value = "/files", method = RequestMethod.POST)
  public String upload(@RequestParam("files") MultipartFile[] uploadingFiles) throws IOException {
    for(MultipartFile uploadedFile : uploadingFiles) {
      competitionFileService.save(uploadedFile.getOriginalFilename(), uploadedFile.getBytes());
    }
    return "";
  }
}
