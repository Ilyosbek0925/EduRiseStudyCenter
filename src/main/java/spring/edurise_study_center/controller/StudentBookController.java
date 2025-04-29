package spring.edurise_study_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.edurise_study_center.DTO.request.GroupDto;
import spring.edurise_study_center.DTO.response.StudentBookResponse;
import spring.edurise_study_center.entity.StudentBooks;
import spring.edurise_study_center.service.StudentBookService;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/book")
@RestController
public class StudentBookController {
    @Autowired
    StudentBookService bookService;

    @PostMapping("uploadStudentBook")
    public ResponseEntity<StudentBookResponse> uploadBook(@RequestParam(name = "file") MultipartFile file,
                                                          @RequestParam(name = "group") Integer group
                                                          ) throws IOException {

        StudentBookResponse upload = bookService.upload(file, group);
        return ResponseEntity.ok(upload);

    }

    @GetMapping("downloadBook/{serverName}")
    public ResponseEntity<byte[]> dowloadBook(@PathVariable String serverName) throws IOException {
        StudentBookResponse download = bookService.downloadBookByServerName(serverName);
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + download.getOriginalName() + "\"")
                .contentType(MediaType.valueOf(download.getContextType()))
                .body(download.getByteFile());
    }

    @GetMapping("getFilesByGroupId/{groupId}")
    public ResponseEntity<List<StudentBookResponse>> downloadByGroupId(@PathVariable Integer groupId) throws IOException {
        List<StudentBookResponse> groupFiles = bookService.downloadBooksByGroupId(groupId);
        return ResponseEntity.ok(groupFiles);
    }

    @GetMapping("getFileByBookId/{bookId}")
    public ResponseEntity<byte[]> downloadByBookId(@PathVariable Integer bookId) throws IOException {
        StudentBookResponse book = bookService.downloadBookByBookId(bookId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + book.getOriginalName() + "\"")
                .contentType(MediaType.parseMediaType(book.getContextType()))
                .body(book.getByteFile());
    }

}
