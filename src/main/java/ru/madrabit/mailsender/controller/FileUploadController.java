package ru.madrabit.mailsender.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.madrabit.mailsender.exception.StorageFileNotFoundException;
import ru.madrabit.mailsender.service.FileSystemStorageService;

@RestController
@RequestMapping("files/")
@Api
public class FileUploadController {

    private final FileSystemStorageService fileSystemStorageService;

    @Autowired
    public FileUploadController(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @GetMapping("/")
    public ResponseEntity<String> listUploadedFiles(Model model) throws IOException {

        final List<String> serveFile = fileSystemStorageService.loadAll().map(
                path -> path.getFileName().toString())
                .collect(Collectors.toList());
        model.addAttribute("files", serveFile);

        return ResponseEntity.ok(serveFile.toString());
    }

    @GetMapping("{filename}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource file = fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        fileSystemStorageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
