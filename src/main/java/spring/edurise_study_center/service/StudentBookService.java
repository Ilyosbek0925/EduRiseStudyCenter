package spring.edurise_study_center.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.edurise_study_center.DTO.response.StudentBookResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.StudentBooks;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.repository.StudentBookRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentBookService {
    @Autowired
    AmazonS3 s3Client;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudentBookRepository studentBookRepository;

    public StudentBookResponse upload(MultipartFile multipartFile,  Integer groupId) throws IOException {
        String serverName = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = multipartFile.getOriginalFilename();
        serverName = serverName + originalFilename.substring(originalFilename.lastIndexOf("."));
        ObjectMetadata metadata = new ObjectMetadata();
        StudentBooks studentBooks = new StudentBooks();
        studentBooks.setServerName(serverName);
        studentBooks.setSize(multipartFile.getSize());
        studentBooks.setOriginalName(originalFilename);
        studentBooks.setDownloadUrl("http://localhost:5656/api/book/downloadBook/" + serverName);
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        studentBooks.setGroup(group);
        studentBooks.setContextType(multipartFile.getContentType());
        String bucketName = "mybacket18784";
        s3Client.putObject("mybacket18784", serverName, multipartFile.getInputStream(), metadata);
        System.out.println("✅ File uploaded successfully!");
        StudentBooks save = studentBookRepository.save(studentBooks);
        return StudentBookResponse.builder()
                .originalName(originalFilename)
                .downloadUrl(save.getDownloadUrl())
                .bookId(save.getId())
                .build();
    }

    public StudentBookResponse download(StudentBooks studentBook) throws IOException {

        S3Object s3Object = s3Client.getObject("mybacket18784", studentBook.getServerName());
        byte[] content = null;
        try {
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            content = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StudentBookResponse.builder()
                .originalName(studentBook.getOriginalName())
                .contextType(studentBook.getContextType())
                .byteFile(content)
                .build();
    }

    public StudentBookResponse downloadBookByBookId(Integer bookId) throws IOException {
        StudentBooks studentBooks = studentBookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
      return  download(studentBooks);
    }

    public List<StudentBookResponse> downloadBooksByGroupId(Integer groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
    return downloadBooks(group.getStudentBooks());
    }

    public StudentBookResponse downloadBookByServerName(String serverName) throws IOException {
        Optional<StudentBooks> studentBook = studentBookRepository.findByServerName(serverName);
        return download(studentBook.orElseThrow(() -> new RuntimeException()));
    }




    public List<StudentBookResponse> downloadBooks(List<StudentBooks> studentBooks) {
        List<StudentBookResponse> responses = new ArrayList<>();
        for (StudentBooks studentBook : studentBooks) {
                StudentBookResponse response = StudentBookResponse.builder()
                        .originalName(studentBook.getOriginalName())
                        .contextType(studentBook.getContextType())
                        .downloadUrl(studentBook.getDownloadUrl())
                        .build();
                responses.add(response);
        }
        return responses;
    }


}
